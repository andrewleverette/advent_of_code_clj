(ns advent-of-code-2015.day-09
  "--- Day 9: All in a Single Night ---
  https://adventofcode.com/2015/day/9"
  (:require [clojure.string :as string]
            [clojure.math.combinatorics :as c]
            [advent-of-code.utils :as utils]))

(def label->keyword (comp keyword string/lower-case))

(defn parse-connection
  "Convert a line of the form 'start to end = distance' 
  into the form [#{start end} distance]"
  [line]
  (let [[_ start end distance] (re-matches #"(\w+) to (\w+) = (\d+)" line)]
    [#{(label->keyword start)
       (label->keyword end)}
     (parse-long distance)]))

(defn connections->graph
  [connections]
  (into {} connections))

(defn vertices
  "Get all the vertices in the graph
  and return them as a set"
  [graph]
  (set (apply concat (keys graph))))

(defn next-segment-distance
  "Given a graph, a distance and vertex tuple, 
  and the next vertex, return the cummulative distance
  to the next vertex."
  [graph [d curr] nxt]
  [(+ d (graph #{curr nxt})) nxt])

(defn distance
  "Given a graph and a path, return the total distance of the path"
  [graph path]
  (->> path
       rest
       (reduce (partial next-segment-distance graph) [0 (first path)])
       first))

(defn distances
  "Find all the distances for all the possible paths in the graph"
  [graph]
  (->> graph
       vertices
       c/permutations
       (map (partial distance graph))
       set))

(defn distances-apply
  "Apply a function to the set of distances 
  of all the possible paths in the graph"
  [f graph]
  (->> graph
       distances
       (apply f)))

(defn part-1
  [input]
  (->> input
       (utils/parse-input parse-connection)
       connections->graph
       (distances-apply min)))

(defn part-2
  [input]
  (->> input
       (utils/parse-input parse-connection)
       connections->graph
       (distances-apply max)))
