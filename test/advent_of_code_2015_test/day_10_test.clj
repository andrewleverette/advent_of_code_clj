(ns advent-of-code-2015-test.day-10-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-10 :refer [length-of-nth-sequence next-sequence partition-and-count]]))

(deftest next-sequence-tests
  (testing "Next sequence should return the next sequence of digits given an input"
    (is (= "11" (next-sequence "1")))
    (is (= "21" (next-sequence "11")))
    (is (= "1211" (next-sequence "21")))
    (is (= "111221" (next-sequence "1211")))))

(deftest length-of-nth-tests
  (testing "Length of nth sequence should return the length of the nth sequence given an input"
    (is (= 2 (length-of-nth-sequence 1 "1")))
    (is (= 2 (length-of-nth-sequence 2 "1")))
    (is (= 4 (length-of-nth-sequence 3 "1")))
    (is (= 6 (length-of-nth-sequence 4 "1")))))
