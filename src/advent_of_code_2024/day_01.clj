(ns advent-of-code-2024.day-01
  "--- Day 1: Historian Hysteria ---
  https://adventofcode.com/2024/day/1"
  (:require [advent-of-code.utils :as utils]))

(defn total-distance-between-lists
  "Given two sorted lists, return the sum of the absolute difference between each pair of numbers"
  [list-1 list-2]
  (reduce + (map #(Math/abs (- %1 %2)) list-1 list-2)))

(defn similarity-score
  "Given a frequency map and a number, return the similarity score"
  [frequency-map number]
  (* number (frequency-map number 0)))

(defn total-similarity-score
  "Given a sequence of numbers and a frequency map, return the total similarity score"
  [numbers frequency-map]
  (reduce (fn [total number]
            (+ total (similarity-score frequency-map number)))
          0
          numbers))

(defn- pairs->vectors
  "Given a sequence of pairs, return a pair of
  vectors containing the first and second elements of each pair"
  [pairs]
  (reduce (fn [[list-1 list-2] [a b]]
            [(conj list-1 a) (conj list-2 b)])
          [[] []]
          pairs))

(defn part-1
  [input]
  (let [pairs (utils/parse-long-pairs input)
        [list-1 list-2] (pairs->vectors pairs)
        sorted-list-1 (sort list-1)
        sorted-list-2 (sort list-2)]
    (total-distance-between-lists sorted-list-1 sorted-list-2)))

(defn part-2
  [input]
  (let [pairs (utils/parse-long-pairs input)
        [list-1 list-2] (pairs->vectors pairs)
        frequency-map (frequencies list-2)]
    (total-similarity-score list-1 frequency-map)))
