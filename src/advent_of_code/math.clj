(ns advent-of-code.math)

(defn distance [a b]
  (Math/abs (- a b)))

(defn sum [ns]
  (reduce + ns))

