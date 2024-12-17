(ns advent-of-code-2024.core
  (:require
   [advent-of-code-2024.day-01 :as day01]
   [advent-of-code-2024.day-02 :as day02]
   [advent-of-code-2024.day-03 :as day03]))

(defmulti runner (fn [tag _] tag))
(defmethod runner :day1.1 [_ input] (day01/part-1 input))
(defmethod runner :day1.2 [_ input] (day01/part-2 input))
(defmethod runner :day2.1 [_ input] (day02/part-1 input))
(defmethod runner :day2.2 [_ input] (day02/part-2 input))
(defmethod runner :day3.1 [_ input] (day03/part-1 input))
(defmethod runner :day3.2 [_ input] (day03/part-2 input))
(defmethod runner :default [_ _] "Invalid day and part combination")
