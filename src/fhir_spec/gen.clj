(ns fhir-spec.gen)

(def native-primitives
  {"boolean" 'boolean?
   "decimal" 'number?
   "integer" 'integer?
   "integer64" 'integer?
   "positiveInt" 'pos-int?
   "string" 'string?
   "unsignedInt" 'pos-int?})

(defn primitives-ns []
  '(ns primitives
     (:require [clojure.spec.alpha :as s])))

(defn primitive [definition]
  (letfn [(sd-val [sd]
            (->> (get-in sd [:snapshot :element])
                 (filter (comp #{(str (:name sd) ".value")} :id))
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
    (let [value (sd-val definition)
          sym (symbol (str ":fhir/" (:name definition)))]
      [(format ";; %s" (:short value))
       (format ";; %s" (:url definition))
       (list 's/def sym (or (get native-primitives (val-type value))
                            (list 's/and 'string? (list 'partial 're-matches (val-regex value)))))])))