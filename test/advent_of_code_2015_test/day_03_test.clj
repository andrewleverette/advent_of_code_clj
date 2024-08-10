(ns advent-of-code-2015-test.day-03-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-03 :refer [distinct-deliveries
                                                next-position
                                                parallel-distinct-deliveries
                                                split-delivery-directions]]))

(deftest next-position-tests
  (testing "Test moving from origin"
    (is (= [0 1] (next-position [0 0] \^)))
    (is (= [1 0] (next-position [0 0] \>)))
    (is (= [0 -1] (next-position [0 0] \v)))
    (is (= [-1 0] (next-position [0 0] \<))))
  (testing "Test moving from non-origin"
    (is (= [0 2] (next-position [0 1] \^)))
    (is (= [2 0] (next-position [1 0] \>)))
    (is (= [0 -2] (next-position [0 -1] \v)))
    (is (= [-2 0] (next-position [-1 0] \<))))
  (testing "Test multiple moves from origin"
    (is (= [0 0] (reduce next-position [0 0] "^>v<")))
    (is (= [0 4] (reduce next-position [0 0] "^^^^")))
    (is (= [4 0] (reduce next-position [0 0] ">>>>")))
    (is (= [0 -4] (reduce next-position [0 0] "vvvv")))
    (is (= [-4 0] (reduce next-position [0 0] "<<<<")))))

(deftest distinct-deliveries-tests
  (testing "No instructions given should return 1"
    (is (= 1 (count (distinct-deliveries ""))))
    (is (= 2 (count (distinct-deliveries "^"))))
    (is (= 4 (count (distinct-deliveries "^>v<"))))
    (is (= 2 (count (distinct-deliveries "^v^v^v^v^v"))))))

(deftest split-delivery-direcionts-tests
  (testing "No instructions should return two empty lists"
    (is = (= [[] []] (split-delivery-directions ""))))
  (testing "One instruction should return a list with one instruction and an empty list"
    (is = (= [[\^] []] (split-delivery-directions "^"))))
  (testing "Even number of instructions"
    (is = (= [[\^ \v] [\> \<]] (split-delivery-directions "^>v<")))
    (is = (= [[\^ \^ \^ \^ \^] [\v \v \v \v \v]] (split-delivery-directions "^v^v^v^v^v")))))

(deftest parallel-distinct-deliveries-tests
  (testing "No instructions given should return 1"
    (is (= 1 (count (parallel-distinct-deliveries ""))))
    (is (= 2 (count (parallel-distinct-deliveries "^"))))
    (is (= 3 (count (parallel-distinct-deliveries "^>v<"))))
    (is (= 11 (count (parallel-distinct-deliveries "^v^v^v^v^v"))))))
