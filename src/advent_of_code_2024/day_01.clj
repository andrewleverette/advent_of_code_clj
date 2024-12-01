(ns advent-of-code-2024.day-01
  "--- Day 1: Historian Hysteria ---
  https://adventofcode.com/2024/day/1"
  (:require
   [advent-of-code.math :as m]
   [advent-of-code.parsing :as p]))

(defn total-distance-between-lists
  "Given two sorted lists, return the sum of the absolute difference between each pair of numbers"
  [list-1 list-2]
  (m/sum (map m/distance list-1 list-2)))

(defn similarity-score
  "Given a frequency map and a number, return the similarity score"
  [frequency-map number]
  (* number (frequency-map number 0)))

(defn total-similarity-score
  "Given a sequence of numbers and a frequency map, return the total similarity score"
  [numbers frequency-map]
  (->> numbers
       (map (partial similarity-score frequency-map))
       m/sum))

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
  (let [[list-1 list-2] (->> input
                             p/parse-long-pairs
                             pairs->vectors
                             (map sort))]
    (total-distance-between-lists list-1 list-2)))

(defn part-2
  [input]
  (let [[list-1 list-2] (->> input
                             p/parse-long-pairs
                             pairs->vectors)
        frequency-map (frequencies list-2)]
    (total-similarity-score list-1 frequency-map)))
