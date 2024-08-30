(ns advent-of-code-2015.day-10
  "--- Day 10: Elves Look, Elves Say ---
  https://adventofcode.com/2015/day/10")

(defn next-sequence
  "Given a sequence of digits, return the next sequence
  of the look-and-say sequence"
  [digits]
  (->> digits
       (partition-by identity)
       (mapcat (juxt count first))
       (apply str)))

(defn length-of-nth-sequence
  "Given a number n and a sequence of digits, return
  the length of the nth sequence of the look-and-say sequence"
  [n digits]
  (count (nth (iterate next-sequence digits) n)))

(defn part-1
  [input]
  (length-of-nth-sequence 40 input))

(defn part-2
  [input]
  (length-of-nth-sequence 50 input))
