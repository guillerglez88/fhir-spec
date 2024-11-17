(ns fhir-spec.structure-definition
  (:require
   [clojure.string :as str]))

(defn rev-path-cells
  "Parse attr path into a pair of [branch leaf] where in turn the branch is also
   a reverse path cell. The inner most cell's branch is nil."
  ([s head]
   (let [[h t] (str/split s #"\." 2)]
     (if t
       (rev-path-cells t [head h])
       [head h])))
  ([s]
   (rev-path-cells s nil)))

(defn spec-key
  "Convert item id or path into keyword to be used as a spec key."
  [s]
  (let [parts (str/split s #"\.")]
    (if (= 1 (count parts))
      (keyword "fhir" (first parts))
      (keyword (->> (take (dec (count parts)) parts)
                    (concat ["fhir"])
                    (str/join "."))
               (last parts)))))

(defn val-regex
  "Extract regex from a primitive type value definition."
  [val]
  (->> (:type val)
       (mapcat :extension)
       (filter (comp #{"http://hl7.org/fhir/StructureDefinition/regex"} :url))
       (some (fn [{:keys [valueString]}]
               (read-string (str "#\"" valueString "\""))))))

(defn val-type
  "Extract type name from a primitive type value definition."
  [val]
  (->> (:type val)
       (mapcat :extension)
       (filter (comp #{"http://hl7.org/fhir/StructureDefinition/structuredefinition-fhir-type"} :url))
       (some :valueUrl)))

(defn attrs [sd]
  (letfn [(expand-codes [{:keys [id type min max]}]
            (for [{:keys [code]} type
                  :let [actual-code (or (val-type {:type type}) code)
                        name (str/replace id #"\[x\]" (str/capitalize actual-code))]]
              {:id name, :code actual-code, :min min, :max max}))]
    (->> (:content sd)
         (remove (comp #{(:type sd)} :id))
         (mapcat expand-codes)
         (group-by (comp first rev-path-cells :id))
         (map (juxt #(->> (first %) (flatten) (remove nil?)) second))
         (sort-by (comp count first) >)
         (map (juxt #(str/join "." (first %)) second)))))

(comment

  (rev-path-cells "Timing.repeat.count")
  ;;=> [[[nil "Timing"] "repeat"] "count"]

  (rev-path-cells "Timing")
  ;;=> [nil "Timing"]

  (rev-path-cells "Timing.repeat")
  ;;=> [[nil "Timing"] "repeat"]

  (spec-key "dateTime")
  ;;=> :fhir/dateTime

  (spec-key "CodeableConcept")
  ;;=> :fhir/CodeableConcept

  (spec-key "Timing.repeat")
  ;;=> :fhir.Timing/repeat

  (spec-key "Timing.repeat.timeOfDay")
  ;;=> :fhir.Timing.repeat/timeOfDay

  (spec-key "Patient")
  ;;=> :fhir/Patient

  :.)