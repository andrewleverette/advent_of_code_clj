(ns advent-of-code-2015.day-05
  "--- Day 5: Doesn't He Have Intern-Elves For This? ---
  https://adventofcode.com/2015/day/5"
  (:require [advent-of-code.parsing :as p]))

(def vowel-set #{\a \e \i \o \u})
(def disallowed-pairs (set [[\a \b] [\c \d] [\p \q] [\x \y]]))

(defn contains-at-least-three-vowels?
  "Given a string, return true if the string contains at least 3 vowels."
  [s]
  (>= (count (filter vowel-set s)) 3))

(defn contains-double-letters?
  "Given a string, return true if the string contains double letters."
  [s]
  (boolean (re-seq #"(\w)\1" s)))

(defn not-contains-disallowed-pairs?
  "Given a sequence of pairs, return true if the string does not contain any of the pairs."
  [pairs s]
  (->> s
       (partition 2 1)
       (some pairs)
       boolean
       not))

(defn contains-non-overlapping-pairs?
  "Given a string, return true if the string contains non-overlapping pairs."
  [s]
  (or
   (re-seq #"(\w{2})\1" s)
   (re-seq #"(\w{2})\w+\1" s)))

(defn contains-split-repeated-letters?
  "Given a string, return true if the string contains split repeated letters."
  [s]
  (re-seq #"(\w)\w\1" s))

(def rule-set-1 (every-pred contains-at-least-three-vowels? contains-double-letters? (partial not-contains-disallowed-pairs? disallowed-pairs)))
(def rule-set-2 (every-pred contains-non-overlapping-pairs? contains-split-repeated-letters?))

(defn nice-string? [pred s] (pred s))

(defn part-1
  [input]
  (->> input
       p/parse-input
       (filter (partial nice-string? rule-set-1))
       count))

(defn part-2
  [input]
  (->> input
       p/parse-input
       (filter (partial nice-string? rule-set-2))
       count))
