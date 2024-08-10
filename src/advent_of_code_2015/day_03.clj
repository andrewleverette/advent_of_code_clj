(ns advent-of-code-2015.day-03
  "--- 2015 Day 3: Perfectly Spherical Houses in a Vacuum ---
  https://adventofcode.com/2015/day/3")

(def deltas {\^ [0 1]
             \> [1 0]
             \v [0 -1]
             \< [-1 0]})

(defn next-position
  "Given a position and a direction, return the next position."
  [pos direction]
  (map + pos (get deltas direction '(0 0))))

(defn distinct-deliveries
  "Given a string of directions, return the distinct houses visited."
  [directions]
  (->> directions
       (reductions next-position '(0 0))
       set))

(defn split-delivery-directions
  "Given a string of directions, split into two lists of directions."
  [directions]
  (let [partitions (partition-all 2 directions)]
    [(->> partitions (map first) (filter #(not (nil? %))))
     (->> partitions (map second) (filter #(not (nil? %))))]))

(defn process-dual-direction-set
  "Apply a function to two sets of directions, return the result as a set."
  [f s1 s2]
  (->> (concat (f s1) (f s2))
       set))

(defn parallel-distinct-deliveries
  "Given a string of directions, return the distinct houses visited in parallel."
  [directions]
  (let [[instructions1 instructions2] (split-delivery-directions directions)]
    (process-dual-direction-set distinct-deliveries instructions1 instructions2)))

(defn part-1
  [input]
  (-> input
      distinct-deliveries
      count))

(defn part-2
  [input]
  (-> input
      parallel-distinct-deliveries
      count))
