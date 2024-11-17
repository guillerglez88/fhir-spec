(ns fhir-spec.gen
  (:require
   [fhir-spec.struct-def :as sd]))

(def native-primitives
  {"boolean" 'boolean?
   "decimal" 'number?
   "integer" 'integer?
   "integer64" 'integer?
   "positiveInt" 'pos-int?
   "string" 'string?
   "unsignedInt" 'pos-int?})

(defn spec-ns []
  '(ns fhir-spec.data-types
     (:require [clojure.spec.alpha :as s])))

(defn primitive-type [definition]
  (letfn [(sd-val [{:keys [type content]}]
            (->> content
                 (filter (comp #{(str type ".value")} :id))
                 (first)))]
    (let [value (sd-val definition)]
      [(format ";; %s" (:short value))
       (format ";; %s" (:url definition))
       (list 's/def
             (-> definition :type sd/spec-key str symbol)
             (or (get native-primitives (sd/val-type value))
                 (list 's/and 'string? (list 'partial 're-matches (sd/val-regex value)))))])))

(defn complex-type [definition registry]
  (letfn [(required [items required]
            (let [min (get {false 0, true 1} required)]
              (->> items
                   (filter (comp #{min} :min))
                   (mapv (comp sd/spec-key :id)))))]
    (let [self (->> (:content definition)
                    (filter (comp #{(:type definition)} :id))
                    (first))
          attr-groups (sd/attrs definition)]
      (->> (apply
            concat
            [(format ";; %s" (:short self))
             (format ";; %s" (:url definition))
             (when-not (seq attr-groups)
               (list 's/def
                     (-> definition :name sd/spec-key str symbol)
                     (if-let [base (:base definition)]
                       (-> registry (get-in [base :type]) sd/spec-key str symbol)
                       '(constantly true))))]
            (for [[key items] attr-groups]
              (concat
               (for [{:keys [id code max]} items
                     :let [type-sym (if (= "Element" code)
                                      (-> (keyword "fhir" id) str symbol)
                                      (-> code sd/spec-key str symbol))]]
                 (list 's/def
                       (-> id sd/spec-key str symbol)
                       (if (= "*" max)
                         (list 's/coll-of type-sym ':kind 'vector? ':distinct 'true)
                         type-sym)))
               [(list 's/def
                      (if (= (:type definition) key)
                        (-> key sd/spec-key str symbol)
                        (-> (keyword "fhir" key) str symbol))
                      (if (seq items)
                        (list 's/merge
                              (-> registry
                                  (get-in [(:base definition) :type])
                                  (sd/spec-key)
                                  (str)
                                  (symbol))
                              (let [req (required items true)
                                    opt (required items false)]
                                (cond-> (list 's/keys)
                                  (seq req) (concat [:req req])
                                  (seq opt) (concat [:opt opt]))))
                        (if-let [base (:base definition)]
                          (-> registry
                              (get-in [base :type])
                              (sd/spec-key)
                              (str)
                              (symbol))
                          '(constantly true))))])))
           (remove nil?)))))