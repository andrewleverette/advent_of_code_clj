(ns advent-of-code.utils
  (:require [clojure.string :as str]))

(defn parse-input
  "Split the input string into a sequence of lines.
  If a function is passed, apply it to each line."
  ([input] (str/split-lines input))
  ([f input] (->> input str/split-lines (map f))))
