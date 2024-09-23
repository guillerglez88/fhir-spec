(ns fhir-spec.core
  (:require
   [clojure.java.io :as io]
   [fhir-spec.config :refer [config]]
   [fhir-spec.artifacts :refer [definitions]]
   [fhir-spec.gen :refer [primitive]]))

(defn write-primitives []
 (with-open [wx (io/writer (io/file (str (:base-dir config) "/primitives.clj")) :append true)]
   (.write wx (str '(ns primitives (:require [clojure.spec.alpha :as s]))))
   (.newLine wx)
   (.newLine wx)
   (doseq [{:keys [resource]} (:entry (definitions "tmp/profiles-types.json"))
           :when (#{"primitive-type"} (:kind resource))]
     (.write wx (str (primitive resource)))
     (.newLine wx))))

(write-primitives)
