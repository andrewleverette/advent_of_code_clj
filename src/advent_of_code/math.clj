(ns advent-of-code.math)

(defn distance [a b]
  (abs (- a b)))

(defn sum
  ([coll] (reduce + coll))
  ([f coll] (transduce (map f) + coll)))

