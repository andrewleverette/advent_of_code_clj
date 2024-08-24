(ns advent-of-code-2015.day-08
  (:require [clojure.string :as str]
            [advent-of-code.utils :as utils]))

(def matchers
  [#"\\\""
   #"\\\\"
   #"\\x[0-9a-f]{2}"])

(defn replace-escape-sequences
  "Replace all escape sequences in a string with a single '.'"
  [s]
  (reduce
   (fn [encoding match] (str/replace encoding match "."))
   s matchers))

(defn in-memory-length
  "Retrun the length of the string minus 2 for the enclosing quote characters."
  [s]
  (->> s
       replace-escape-sequences
       count
       (+ -2)))

(defn encode-string
  "Encode a string with double quotes and backslashes escaped replacing them with '..'"
  [s]
  (str/replace s #"[\"\\]" ".."))

(defn encoded-string-length
  "Retrun the length of the encoded string plus 2 for the enclosing quote characters."
  [s]
  (->> s
       encode-string
       count
       (+ 2)))

(defn calculate-character-difference
  "Calculate the difference between the length of the input and the functions applied to each string"
  [f g words]
  (->> words
       (map #(- (f %) (g %)))
       (reduce + 0)))

(defn part-1
  [input]
  (->> input
       utils/parse-input
       (calculate-character-difference count in-memory-length)))

(defn part-2
  [input]
  (->> input
       utils/parse-input
       (calculate-character-difference encoded-string-length count)))
