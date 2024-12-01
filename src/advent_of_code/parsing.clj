(ns advent-of-code.parsing
  (:require [clojure.string :as str]))

(defn parse-input
  "Split the input string into a sequence of lines.
  If a function is passed, apply it to each line."
  ([input] (str/split-lines input))
  ([f input] (->> input str/split-lines (map f))))

(defn parse-pairs
  [input [f g]]
  (->> input
       str/split-lines
       (map (fn [line]
              (let [[a b] (str/split line #"\s+")]
                [(f a) (g b)])))))

(defn parse-long-pairs
  "Parse a string into a sequence of pairs of longs"
  [input]
  (parse-pairs input [parse-long parse-long]))
