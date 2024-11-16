(ns fhir-spec.gen
  (:require
   [clojure.string :as str]))

(def native-primitives
  {"boolean" 'boolean?
   "decimal" 'number?
   "integer" 'integer?
   "integer64" 'integer?
   "positiveInt" 'pos-int?
   "string" 'string?
   "unsignedInt" 'pos-int?})

(defn id->keyword [id]
  (let [parts (str/split id #"\.")]
    (if (= 1 (count parts))
      (keyword "fhir" (first parts))
      (keyword (->> (take (dec (count parts)) parts)
                    (concat ["fhir"])
                    (str/join "."))
               (last parts)))))

(defn id->symbol [id]
  (-> (id->keyword id)
      (str)
      (symbol)))

(defn spec-ns []
  '(ns primitives
     (:require [clojure.spec.alpha :as s])))

(defn primitive [definition]
  (letfn [(sd-val [{:keys [type content]}]
            (->> content
                 (filter (comp #{(str type ".value")} :id))
                 (first)))
          (val-type [val]
            (->> (:type val)
                 (mapcat :extension)
                 (filter (comp #{"http://hl7.org/fhir/StructureDefinition/structuredefinition-fhir-type"} :url))
                 (some :valueUrl)))
          (val-regex [val]
            (->> (:type val)
                 (mapcat :extension)
                 (filter (comp #{"http://hl7.org/fhir/StructureDefinition/regex"} :url))
                 (some (fn [{:keys [valueString]}]
                         (read-string (str "#\"" valueString "\""))))))]
    (let [value (sd-val definition)]
      [(format ";; %s" (:short value))
       (format ";; %s" (:url definition))
       (list 's/def
             (id->symbol (:type definition))
             (or (get native-primitives (val-type value))
                 (list 's/and 'string? (list 'partial 're-matches (val-regex value)))))])))

(defn complex [definition registry]
  (letfn [(items [content]
            (for [{:keys [id type min max]} content
                  {:keys [code]} type
                  :when (not= id (:type definition))]
              {:id id
               :type type
               :code code
               :min min
               :max max
               :key (if (= 1 (count type))
                      (id->keyword id)
                      (id->keyword (str/replace id #"\[x\]" (str/capitalize code))))}))
          (required [items required]
            (let [min (get {false 0, true 1} required)]
              (->> items
                   (filter (comp #{min} :min))
                   (mapv :key))))]
    (let [self (->> (:content definition)
                    (filter (comp #{(:type definition)} :id))
                    (first))
          items (items (:content definition))]
      (concat
       [(format ";; %s" (:short self))
        (format ";; %s" (:url definition))]
       (for [{:keys [key code max]} items
             :let [type-sym (id->symbol code)]]
         (list 's/def
               (symbol (str key))
               (if (= "*" max)
                 (list 's/coll-of type-sym ':kind 'vector? ':distinct 'true)
                 type-sym)))
       [(list 's/def
              (id->symbol (:type definition))
              (if (seq items)
                (list 's/merge
                      (id->symbol (get-in registry [(:base definition) :type]))
                      (let [req (required items true)
                            opt (required items false)]
                        (cond-> (list 's/keys)
                          (seq req) (concat [:req req])
                          (seq opt) (concat [:opt opt]))))
                (id->symbol (get-in registry [(:base definition) :type]))))]))))