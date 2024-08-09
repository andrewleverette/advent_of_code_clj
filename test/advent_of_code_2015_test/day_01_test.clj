(ns advent-of-code-2015-test.day-01-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-01 :refer [next-floor find-floor first-basement-entry]]))

(deftest next-floor-tests
  (testing "No instructions given should return current floor"
    (is (= 0 (next-floor 0 nil)))
    (is (= 1 (next-floor 1 nil))))
  (testing "Based on instructions, should return next floor which should be +1 or -1"
    (is (= 1 (next-floor 0 \()))
    (is (= 2 (next-floor 1 \()))
    (is (= -1 (next-floor 0 \))))
    (is (= 0 (next-floor 1 \))))))

(deftest find-floor-tests
  (testing "No instructions given should return 0"
    (is (= 0 (find-floor nil)))
    (is (= 0 (find-floor ""))))
  (testing "When given one instruction, should return the 1 or -1"
    (is (= 1 (find-floor "(")))
    (is (= -1 (find-floor ")"))))
  (testing "When given multiple instructions, should return the floor"
    (is (= 0 (find-floor ")))(((")))
    (is (= 3 (find-floor "(((")))))

(deftest first-basement-entry-tests
  (testing "No instructions given should return nil"
    (is (= nil (first-basement-entry nil)))
    (is (= nil (first-basement-entry "")))))
