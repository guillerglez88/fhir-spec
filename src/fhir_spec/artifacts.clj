(ns fhir-spec.artifacts
  (:require
   [clj-http.client :as client]
   [clojure.java.io :as io]
   [fhir-spec.config :refer [config]]
   [clojure.data.json :as json])
  (:import
   (java.util.zip ZipInputStream)))

(defn definitions [f]
  (-> (io/file f)
      (slurp)
      (json/read-str :key-fn keyword)))

(defn download-spec! []
  (letfn [(extract [stream base-dir]
            (loop [zip stream
                   path base-dir]
              (when-let [entry (.getNextEntry zip)]
                (let [entry-path (str path "/" (.getName entry))]
                  (if (.isDirectory entry)
                    (do (.mkdirs (io/file entry-path))
                        (recur zip entry-path))
                    (do (io/copy zip (io/file entry-path))
                        (recur zip path)))))))]
    (-> {:url (:definitions-url config)
         :method :get
         :as :stream}
        (client/request)
        (:body)
        (ZipInputStream.)
        (extract (:base-dir config)))))
