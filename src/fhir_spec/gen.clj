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
  (let [[part1 part2] (str/split id #"\.")]
    (if part2
      (keyword (str "fhir." part1) part2)
      (keyword "fhir" part1))))

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
  (letfn [(required [items required]
            (let [min (get {false 0, true 1} required)]
              (->> items
                   (filter (comp #{min} :min))
                   (mapv (comp id->symbol :id)))))]
    (let [self (->> (:content definition)
                    (filter (comp #{(:type definition)} :id))
                    (first))
          items (->> (:content definition)
                     (remove (comp #{(:type definition)} :id))
                     (vec))]
      (concat
       [(format ";; %s" (:short self))
        (format ";; %s" (:url definition))]
       (for [{:keys [id max type]} items
             {:keys [code]} type
             :when (not= id (:type definition))
             :let [type-sym (id->symbol code)]]
         (list 's/def
               (id->keyword id)
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