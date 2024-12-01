(ns advent-of-code.math)

(defn distance [a b]
  (abs (- a b)))

(defn sum [ns]
  (reduce + ns))

