(ns advent-of-code.enumerate)

(defn count-when
  "Given a predicate and a sequence, return the number
  of elements in the sequence that satisfy the predicate"
  [pred coll]
  (count (filter pred coll)))

(defn drop-subranges
  "Given a length n and a sequence, return a sequence
  of all combinations of subsequences without the subrange
  of length n"
  [n coll]
  (if (and (pos? n) (< n (count coll)))
    (map #(concat (take % coll) (drop (+ n %) coll))
         (range (- (count coll) n -1)))
    '()))
