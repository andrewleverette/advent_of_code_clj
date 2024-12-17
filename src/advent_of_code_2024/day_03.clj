(ns advent-of-code-2024.day-03
  (:require [advent-of-code.parsing :as p]))

(def ^:private op-pattern #"mul\((\d{1,3}),(\d{1,3})\)")

(def ^:private op-pattern-with-conditions
  #"mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)")

(defn- line->operands
  [line]
  (->> line
       (re-seq op-pattern)
       (map (fn [[_ x y]] [(parse-long x) (parse-long y)]))))

(defn- line->operands-with-conditions
  [line]
  (->> line
       (re-seq op-pattern-with-conditions)
       (map (fn [[op x y]]
              (case op
                "do()" [:enable]
                "don't()" [:disable]
                [:mul (parse-long x) (parse-long y)])))))

(defn part-1
  [input]
  (->> input
       (p/parse-input line->operands)
       (apply concat)
       (reduce (fn [sum [x y]] (+ sum (* x y))) 0)))

(defn part-2
  [input]
  (loop [[[op & operands] & ops] (->> input
                                      (p/parse-input line->operands-with-conditions)
                                      (apply concat))
         enabled true
         sum 0]
    (if (nil? op)
      sum
      (case op
        :enable (recur ops true sum)
        :disable (recur ops false sum)
        :mul (recur ops enabled (if enabled
                                  (+ sum (apply * operands))
                                  sum))))))
