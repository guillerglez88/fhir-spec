(ns fhir-spec.core
  (:require
   [clojure.java.io :as io]
   [fhir-spec.config :refer [config]]
   [fhir-spec.artifacts :refer [read-file download-spec!]]
   [fhir-spec.gen :as gen]
   [fhir-spec.struct-def :as sd]
   [clojure.pprint :as pp]))

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
    (doseq [{:keys [kind] :as item} (map (partial get definitions)
                                         (read-string (slurp "resources/topo.edn")))
            :when (= "complex-type" kind)]
      (write (gen/complex-type item definitions))
      (println))))

(defn print-topo [definitions]
  (->> (vals definitions)
       (sd/graph)
       (sd/topo-sort)
       (remove #{"http://hl7.org/fhir/StructureDefinition/ElementDefinition"})
       (vec)
       (pp/pprint)))

(comment

  (download-spec!)

  (with-open [wx (io/writer (io/file "src/fhir_spec/data_types.clj") :append true)]
    (binding [*out* wx]
      (print-specs (load-definitions))))

  :.)
