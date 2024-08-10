(ns advent-of-code-2015-test.day-04-test
  (:require [clojure.test :refer [is testing deftest]]
            [advent-of-code-2015.day-04 :refer [find-min-number]]))

(deftest part-1-samples
  (testing "Puzzle input examples for part 1"
    (is (= 609043 (find-min-number "00000" "abcdef")))
    (is (= 1048970 (find-min-number "00000" "pqrstuv")))))
