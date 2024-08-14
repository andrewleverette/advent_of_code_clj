(ns advent-of-code-2015.core
  (:require
   [advent-of-code-2015.day-01 :as day01]
   [advent-of-code-2015.day-02 :as day02]
   [advent-of-code-2015.day-03 :as day03]
   [advent-of-code-2015.day-04 :as day04]
   [advent-of-code-2015.day-05 :as day05]
   [advent-of-code-2015.day-06 :as day06]))

(defmulti runner (fn [tag _] tag))
(defmethod runner :day1.1 [_ input] (day01/part-1 input))
(defmethod runner :day1.2 [_ input] (day01/part-2 input))
(defmethod runner :day2.1 [_ input] (day02/part-1 input))
(defmethod runner :day2.2 [_ input] (day02/part-2 input))
(defmethod runner :day3.1 [_ input] (day03/part-1 input))
(defmethod runner :day3.2 [_ input] (day03/part-2 input))
(defmethod runner :day4.1 [_ input] (day04/part-1 input))
(defmethod runner :day4.2 [_ input] (day04/part-2 input))
(defmethod runner :day5.1 [_ input] (day05/part-1 input))
(defmethod runner :day5.2 [_ input] (day05/part-2 input))
(defmethod runner :day6.1 [_ input] (day06/part-1 input))
(defmethod runner :day6.2 [_ input] (day06/part-2 input))

