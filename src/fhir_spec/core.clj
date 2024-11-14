(ns fhir-spec.core
  (:require
   [clojure.java.io :as io]
   [fhir-spec.config :refer [config]]
   [fhir-spec.artifacts :refer [definitions download-spec!]]
   [fhir-spec.gen :as gen]))

(def definitions-by-kind
  (delay
    (->> (:entry (definitions (str (:base-dir config) "/profiles-types.json")))
         (map :resource)
         (group-by :kind))))

(defn write [wx lines]
  (doseq [line lines]
    (if (string? line)
      (.write wx line)
      (.write wx (str line)))
    (.newLine wx)))

(defn write-primitives []
  (let [f (io/file (str (:base-dir config) "/primitives.clj"))]
    (io/delete-file f true)
    (with-open [wx (io/writer f :append true)]
      (write wx [(gen/primitives-ns)])
      (.newLine wx)
      (doseq [definition (get @definitions-by-kind "primitive-type")]
        (write wx (gen/primitive definition))
        (.newLine wx)))))

(comment

  (download-spec!)

  (write-primitives)

  ;; CodeableConcept: #9
  (nth (get @definitions-by-kind "complex-type") 9)

  :.)
