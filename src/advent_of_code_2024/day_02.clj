(ns advent-of-code-2024.day-02
  "--- Day 2: Red-Nosed Reports ---
  https://adventofcode.com/2024/day/2"
  (:require
   [advent-of-code.enumerate :as enum]
   [advent-of-code.parsing :as p]))

(defn- all-increasing-in-range?
  [deltas]
  (every? #(<= 1 % 3) deltas))

(defn- all-decreasing-in-range?
  [deltas]
  (every? #(<= -3 % -1) deltas))

(defn report-is-safe?
  "Given a report, return true if the report is safe.
  A report is safe if levels are all increasing or all decreasing
  and adjacent levels differ by at least 1 and at most 3"
  [report]
  (when (seq report)
    (let [deltas (->> report
                      (partition 2 1)
                      (map #(apply - %)))]
      (or (all-increasing-in-range? deltas)
          (all-decreasing-in-range? deltas)))))

(defn report-is-safe-with-dampener?
  "Given a report, return true if the report is safe with dampening.
  A report is safe with dampening if the report is safe or the report
  is safe with one level ignored"
  [report]
  (when (seq report)
    (->> report
         (enum/drop-subranges 1)
         (cons report)
         (some report-is-safe?))))

(defn total-safe-reports
  [safe? reports]
  (enum/count-when safe? reports))

(defn str->report
  [s]
  (map parse-long (re-seq #"\d+" s)))

(defn part-1
  [input]
  (->> input
       (p/parse-input str->report)
       (total-safe-reports report-is-safe?)))

(defn part-2
  [input]
  (->> input
       (p/parse-input str->report)
       (total-safe-reports report-is-safe-with-dampener?)))
