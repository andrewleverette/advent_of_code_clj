(ns advent-of-code-2015.day-04
  "--- Day 4: The Ideal Stocking Stuffer --)
  https://adventofcode.com/2015/day/4"
  (:require [clojure.string :as str]
            [clj-commons.digest :as digest]))

(defn find-min-number
  "Given a prefix and a base, find the lowest positive number n where
  the MD5 hash of base and some n starts with the prefix."
  [prefix base]
  (loop [n 1]
    (let [hash (digest/md5 (str base n))]
      (if (str/starts-with? hash prefix)
        n
        (recur (inc n))))))

(defn part-1
  [input]
  (find-min-number "00000" input))

(defn part-2
  [input]
  (find-min-number "000000" input))
