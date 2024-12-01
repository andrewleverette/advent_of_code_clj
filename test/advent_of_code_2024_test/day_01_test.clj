(ns advent-of-code-2024-test.day-01-test
  (:require [clojure.test :refer [deftest is testing]]
            [advent-of-code-2024.day-01 :as day-01]))

(deftest total-distance-between-lists-tests
  (testing "Empty or nil lists should return 0"
    (is (= 0 (day-01/total-distance-between-lists nil nil)))
    (is (= 0 (day-01/total-distance-between-lists [] []))))
  (testing "Equal lists that are the same should return 0"
    (is (= 0 (day-01/total-distance-between-lists [1] [1])))
    (is (= 0 (day-01/total-distance-between-lists [1 2 3] [1 2 3]))))
  (testing "Returns the total distance between the lists"
    (is (= 3 (day-01/total-distance-between-lists [1 2 3] [2 3 4])))
    (is (= 9 (day-01/total-distance-between-lists [1 2 3] [4 5 6]))))
  (testing "Lists with different lengths should only compare up to the shortest length"
    (is (= 0 (day-01/total-distance-between-lists [] [1])))
    (is (= 1 (day-01/total-distance-between-lists [1] [2 3])))))

(deftest similarity-score-tests
  (testing "Number not in frequency map should return 0"
    (is (= 0 (day-01/similarity-score {} 1)))
    (is (= 0 (day-01/similarity-score {1 1} 2))))
  (testing "Returns the correct similarity score"
    (is (= 1 (day-01/similarity-score {1 1} 1)))
    (is (= 2 (day-01/similarity-score {1 2} 1)))
    (is (= 4 (day-01/similarity-score {2 2} 2)))))
