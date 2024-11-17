(ns fhir-spec.core
  (:require
   [fhir-spec.config :refer [config]]
   [fhir-spec.artifacts :refer [read-file download-spec!]]
   [fhir-spec.gen :as gen]))

(defn load-definitions []
  (letfn [(data [{:keys [type url kind name baseDefinition differential]}]
            {:type type
             :url url
             :base baseDefinition
             :kind kind
             :name name
             :content (->> (:element differential)
                           (mapv #(select-keys % [:id :short :type :min :max])))})]
    (let [profile-types (read-file (str (:base-dir config) "/profiles-types.json"))]
      (->> (:entry profile-types)
           (map :resource)
           (map (juxt :url data))
           (into {})))))

(defn print-specs [definitions]
  (letfn [(write [lines]
            (doseq [line lines]
              (println line)))]
    (write [(gen/spec-ns)])
    (println)
    (println ";; --- primitives ---\n")
    (doseq [[_url {:keys [kind] :as item}] definitions
            :when (= "primitive-type" kind)]
      (write (gen/primitive-type item))
      (println))
    (println ";; --- complex ---\n")
    (doseq [[_url {:keys [kind] :as item}] definitions
            :when (= "complex-type" kind)]
      (write (gen/complex-type item definitions))
      (println))))

(comment

  (download-spec!)

  (print-specs (load-definitions))

  (let [defs (load-definitions)]
    #_
    (sd/attrs (get defs "http://hl7.org/fhir/StructureDefinition/Timing"))
    (gen/complex-type (get defs "http://hl7.org/fhir/StructureDefinition/Timing") defs))

  :.)
