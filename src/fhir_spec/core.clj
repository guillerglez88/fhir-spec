(ns fhir-spec.core
  (:require
   [fhir-spec.config :refer [config]]
   [fhir-spec.artifacts :refer [read-file download-spec!]]
   [fhir-spec.gen :as gen]))

(defn load-definitions []
  (letfn [(data [{:keys [type url kind baseDefinition differential]}]
            {:type type
             :url url
             :key (keyword "fhir" type)
             :base baseDefinition
             :kind kind
             :content (->> (:element differential)
                           (mapv #(select-keys % [:id :short :type :min :max])))})]
    (let [profile-types (read-file (str (:base-dir config) "/profiles-types.json"))]
      (->> (:entry profile-types)
           (map :resource)
           (map (juxt :url data))
           (into {})))))

(comment

  (download-spec!)

  (gen/print-primitives (load-definitions))

  :.)
