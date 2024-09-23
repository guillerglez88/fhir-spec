(ns fhir-spec.core
  (:require
   [clojure.java.io :as io]
   [fhir-spec.config :refer [config]]
   [fhir-spec.artifacts :refer [definitions]]
   [fhir-spec.gen :refer [primitive]]))

(def definitions-by-kind
  (delay
    (->> (:entry (definitions "tmp/profiles-types.json"))
         (map :resource)
         (group-by :kind))))

(defn write-primitives []
  (with-open [wx (io/writer (io/file (str (:base-dir config) "/primitives.clj")) :append true)]
    (.write wx (str '(ns primitives (:require [clojure.spec.alpha :as s]))))
    (.newLine wx)
    (.newLine wx)
    (doseq [definition (get @definitions-by-kind "primitive-type")]
      (.write wx (str (primitive definition)))
      (.newLine wx))))

