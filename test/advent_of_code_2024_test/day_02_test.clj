(ns advent-of-code-2024-test.day-02-test
  (:require [clojure.test :refer [deftest is testing]]
            [advent-of-code-2024.day-02 :as day-02]))

(deftest report-is-safe-tests
  (testing "Empty or nil reports should return false"
    (is (not (day-02/report-is-safe? nil)))
    (is (not (day-02/report-is-safe? []))))
  (testing "Reports with less than two levels should return true"
    (is (day-02/report-is-safe? [1])))
  (testing "Reports with adjacent duplicates should return false"
    (is (not (day-02/report-is-safe? [1 1]))))
  (testing "Reports should contain all increasing or all decreasing levels"
    (is (day-02/report-is-safe? [1 2 3]))
    (is (day-02/report-is-safe? [3 2 1]))
    (is (day-02/report-is-safe? [1 2 3])))
  (testing "Adjacent levels should differ by at least one and at most three"
    (is (day-02/report-is-safe? [1 2 3]))
    (is (day-02/report-is-safe? [7 4 1]))
    (is (not (day-02/report-is-safe? [1 1 2])))
    (is (not (day-02/report-is-safe? [1 5 9])))))

(deftest report-is-safe-with-dampener-tests
  (testing "Empty or nil reports should return false"
    (is (not (day-02/report-is-safe-with-dampener? nil)))
    (is (not (day-02/report-is-safe-with-dampener? []))))
  (testing "Reports with less than two levels should return true"
    (is (day-02/report-is-safe-with-dampener? [1])))
  (testing "Reports with at most one level off should be safe"
    (is (day-02/report-is-safe-with-dampener? [1 1]))
    (is (not (day-02/report-is-safe-with-dampener? [1 1 1])))
    (is (day-02/report-is-safe-with-dampener? [1 2 2 3]))
    (is (not (day-02/report-is-safe-with-dampener? [1 2 2 3 3 4])))
    (is (day-02/report-is-safe-with-dampener? [1 2 2 3 4 5]))
    (is (day-02/report-is-safe-with-dampener? [4 3 5 2 1]))))
