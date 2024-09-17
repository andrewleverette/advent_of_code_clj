(ns advent-of-code-2015.day-12
  "--- Day 12: JSAbacusFramework.io ---
  https://adventofcode.com/2015/day/12"
  (:require [clojure.walk :as walk]
            [cheshire.core :as json]))

(defn- contains-red?
  "Returns true if the object contains 'red' as a value"
  [obj]
  (some (fn [node] ((set node) "red")) obj))

(defn find-numbers-in-object
  "Walks the object in pre-order traveral and applies the function f to each node,
  then flattens the result and returns a list of all the numbers in the object"
  [f obj]
  (->> obj
       (walk/prewalk f)
       flatten
       (filter integer?)))

(defn find-all-numbers-in-object
  "Returns a list of all the numbers in the object"
  [obj]
  (find-numbers-in-object #(if (map? %) (seq %) %) obj))

(defn find-non-red-numbers-in-object
  "Returns a list of all the numbers in the object except where a map contains the value 'red'"
  [obj]
  (find-numbers-in-object #(cond (and (map? %) (contains-red? %)) nil
                                 (map? %) (seq %)
                                 :else %) obj))

(defn part-1
  [input]
  (->> input
       json/parse-string
       find-all-numbers-in-object
       (reduce +)))

(defn part-2
  [input]
  (->> input
       json/parse-string
       find-non-red-numbers-in-object
       (reduce +)))
