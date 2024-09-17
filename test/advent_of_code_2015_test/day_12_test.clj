(ns advent-of-code-2015-test.day-12-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-12 :refer [find-all-numbers-in-object find-non-red-numbers-in-object]]))

(defn sum [coll] (reduce + coll))

(deftest test-summation-of-all-numbers
  (testing "A vector of numbers should produce the sum of all the numbers in the vector"
    (is (= 0 (sum (find-all-numbers-in-object []))))
    (is (= 6 (sum (find-all-numbers-in-object [1 2 3]))))
    (is (= 0 (sum (find-non-red-numbers-in-object []))))
    (is (= 6 (sum (find-non-red-numbers-in-object [1 2 3])))))
  (testing "Maps should be sum of all integer values"
    (is (= 0 (sum (find-all-numbers-in-object {:a "1" :b "2" :c "3"}))))
    (is (= 0 (sum (find-non-red-numbers-in-object {:a "1" :b "2" :c "3"}))))
    (is (= 6 (sum (find-all-numbers-in-object {:a 1 :b 2 :c 3}))))
    (is (= 6 (sum (find-all-numbers-in-object {:a 1 :b 2 :c 3})))))
  (testing "Nesting of maps or vectors should return the sum of all numbers"
    (is (= 6 (sum (find-all-numbers-in-object [{:a 1 :b 2 :c 3}]))))
    (is (= 6 (sum (find-non-red-numbers-in-object [{:a 1 :b 2 :c 3}]))))
    (is (= 15 (sum (find-all-numbers-in-object [{:a 1 :b 2} [3 4 5]]))))
    (is (= 15 (sum (find-non-red-numbers-in-object [{:a 1 :b 2} [3 4 5]]))))))

(deftest test-summation-all-numbers-except-red
  (testing "A vector of number should produce the sum of all the numbers in the vector"
    (is (= 0 (sum (find-all-numbers-in-object ["red"]))))
    (is (= 6 (sum (find-all-numbers-in-object [1 2 3 "red"]))))
    (is (= 0 (sum (find-non-red-numbers-in-object ["red"]))))
    (is (= 6 (sum (find-non-red-numbers-in-object [1 2 3 "red"])))))
  (testing "A map should be ignored if it contains 'red' as a value"
    (is (= 5 (sum (find-all-numbers-in-object {:a "red" :b 2 :c 3}))))
    (is (= 0 (sum (find-non-red-numbers-in-object {:a "red" :b 2 :c 3}))))))
