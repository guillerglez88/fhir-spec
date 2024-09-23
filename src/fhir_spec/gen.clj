(ns fhir-spec.gen)

(def native-primitives
  {:fhir/boolean 'string?
   :fhir/decimal 'decimal?
   :fhir/integer 'integer?
   :fhir/integer64 'integer?
   :fhir/positiveInt 'pos-int?
   :fhir/string 'string?
   :fhir/unsignedInt 'pos-int?
   :fhir/xhtml 'string?})

(defn primitive [definition]
  (let [sym (symbol (str ":fhir/" (:name definition)))
        regex (->> (get-in definition [:snapshot :element])
                   (mapcat :type)
                   (mapcat :extension)
                   (filter (comp #{"http://hl7.org/fhir/StructureDefinition/regex"} :url))
                   (some (fn [{:keys [valueString]}]
                           (read-string (str "#\"" valueString "\"")))))]
    (list 's/def sym
          (or
           (get native-primitives (read-string (str sym)))
           (list 's/and 'string? (list 'partial 're-matches regex))))))
