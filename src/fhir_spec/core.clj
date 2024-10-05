(ns fhir-spec.core
  (:require
   [clojure.pprint :as pp]
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
    (pp/pprint '(ns primitives (:require [clojure.spec.alpha :as s])) wx)
    (doseq [definition (get @definitions-by-kind "primitive-type")]
      (.newLine wx)
      (.write wx (format ";; %s \n" (:url definition)))
      (pp/pprint (primitive definition) wx))))

(comment

  (write-primitives)

  (first (get @definitions-by-kind "complex-type"))

  :.)
