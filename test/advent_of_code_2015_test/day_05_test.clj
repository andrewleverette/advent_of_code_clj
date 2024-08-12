(ns advent-of-code-2015-test.day-05-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-05 :refer [contains-at-least-three-vowels?
                                                contains-double-letters?
                                                not-contains-disallowed-pairs?
                                                nice-string?
                                                disallowed-pairs
                                                rule-set-1
                                                rule-set-2]]))

(deftest test-contains-three-vowels-condtion
  (testing "Any string less than 3 characters does not contain 3 vowels"
    (is (false? (contains-at-least-three-vowels? "a")))
    (is (false? (contains-at-least-three-vowels? "a")))
    (is (false? (contains-at-least-three-vowels? "ab"))))
  (testing "Any string of 3 or more characters"
    (is (true? (contains-at-least-three-vowels? "aaa")))
    (is (false? (contains-at-least-three-vowels? "aabb")))
    (is (false? (contains-at-least-three-vowels? "aabbcc")))
    (is (false? (contains-at-least-three-vowels? "aabbccdd")))
    (is (true? (contains-at-least-three-vowels? "aei")))
    (is (true? (contains-at-least-three-vowels? "aeiou")))
    (is (true? (contains-at-least-three-vowels? "xazegov")))
    (is (true? (contains-at-least-three-vowels? "aeiouaeiouaeiou")))))

(deftest test-contains-double-letters-condition
  (testing "Any string less than 2 characters does not contain double letters"
    (is (false? (contains-double-letters? "")))
    (is (false? (contains-double-letters? "a"))))
  (testing "Any string of 2 or more characters"
    (is (true? (contains-double-letters? "aa")))
    (is (false? (contains-double-letters? "ab")))
    (is (true? (contains-double-letters? "aaa")))
    (is (false? (contains-double-letters? "aba")))
    (is (true? (contains-double-letters? "abcdde")))
    (is (true? (contains-double-letters? "aabbccdd")))))

(deftest test-contains-sequential-letters-condition
  (testing "Any string less than 2 characters does not contain sequential letters"
    (is (true? (not-contains-disallowed-pairs? disallowed-pairs "")))
    (is (true? (not-contains-disallowed-pairs? disallowed-pairs "a"))))
  (testing "Any string of 2 or more characters"
    (is (true? (not-contains-disallowed-pairs? disallowed-pairs "aa")))
    (is (false? (not-contains-disallowed-pairs? disallowed-pairs "ab")))
    (is (true? (not-contains-disallowed-pairs? disallowed-pairs "aaa")))
    (is (false? (not-contains-disallowed-pairs? disallowed-pairs "aba")))
    (is (false? (not-contains-disallowed-pairs? disallowed-pairs  "abcdde")))
    (is (false? (not-contains-disallowed-pairs? disallowed-pairs "aabbccdd")))))

(deftest test-nice-string
  (testing "Puzzle input examples for part 1"
    (is (true? (nice-string? rule-set-1 "ugknbfddgicrmopn")))
    (is (true? (nice-string? rule-set-1 "aaa")))
    (is (false? (nice-string? rule-set-1 "jchzalrnumimnmhp")))
    (is (false? (nice-string? rule-set-1 "haegwjzuvuyypxyu")))
    (is (false? (nice-string? rule-set-1 "dvszwmarrgswjxmb"))))
  (testing "Puzzle input examples for part 2"
    (is (true? (nice-string? rule-set-2 "qjhvhtzxzqqjkmpb")))
    (is (true? (nice-string? rule-set-2 "xxyxx")))
    (is (false? (nice-string? rule-set-2 "uurcxstgmygtbstg")))
    (is (false? (nice-string? rule-set-2 "ieodomkazucvgmuy")))))
