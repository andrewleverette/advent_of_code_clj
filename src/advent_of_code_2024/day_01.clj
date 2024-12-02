(ns advent-of-code-2024.day-01
  "--- Day 1: Historian Hysteria ---
  https://adventofcode.com/2024/day/1"
  (:require
   [advent-of-code.math :as m]
   [advent-of-code.parsing :as p]))

(defn total-distance-between-lists
  "Given two sorted lists, return the sum of the absolute difference between each pair of numbers"
  [pairs]
  (m/sum #(apply m/distance %) pairs))

(defn similarity-score
  "Given a frequency map and a number, return the similarity score"
  [frequency-map number]
  (* number (frequency-map number 0)))

(defn total-similarity-score
  "Given a sequence of numbers and a frequency map, return the total similarity score"
  [numbers frequency-map]
  (m/sum #(similarity-score frequency-map %) numbers))

(defn- pairs->vectors
  "Given a sequence of pairs, return a pair of
  vectors containing the first and second elements of each pair"
  [pairs]
  ((juxt (partial map first) (partial map second)) pairs))

(defn part-1
  [input]
  (->> input
       (p/parse-long-pairs pairs->vectors)
       (map sort)
       (apply map vector)
       total-distance-between-lists))

(defn part-2
  [input]
  (let [[list-1 list-2] (p/parse-long-pairs pairs->vectors input)
        frequency-map (frequencies list-2)]
    (total-similarity-score list-1 frequency-map)))
