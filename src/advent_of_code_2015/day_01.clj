(ns advent-of-code-2015.day-01
  "--- 2015 Day 1: Not Quite Lisp --
  https://adventofcode.com/2015/day/1")

(def instruction-map
  {\( 1
   \) -1})

(defn next-floor
  "Takes a floor and an instruction and returns the next floor."
  [floor instruction]
  (+ floor (get instruction-map instruction 0)))

(defn find-floor
  "Takes a list of instructions and returns the floor."
  [instructions]
  (reduce next-floor 0 instructions))

(defn first-basement-entry
  "Takes a list of instructions and returns the first occurrence of the basement."
  [instructions]
  (->> instructions
       (reductions next-floor 0)
       (keep-indexed #(when (neg? %2) %1))
       first))

(defn part-1
  [input]
  (find-floor input))

(defn part-2
  [input]
  (first-basement-entry input))
