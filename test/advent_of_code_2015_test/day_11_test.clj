(ns advent-of-code-2015-test.day-11-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-11 :refer [contains-straight?
                                                not-contains-invalid-characters?
                                                contains-non-overlapping-pairs
                                                string->base26
                                                base26->string
                                                generate-passwords]]))

(deftest password-contains-straight-tests
  (testing "Empty string should return nill"
    (is (nil? (contains-straight? ""))))
  (testing "Any string less than 3 characters should return nil"
    (is (nil? (contains-straight? "a")))
    (is (nil? (contains-straight? "ab"))))
  (testing "A string of 3 characters should return true if it contains a straight"
    (is (true? (contains-straight? "abc")))
    (is (true? (contains-straight? "bcd"))))
  (testing "A straight must be in increasing order"
    (is (nil? (contains-straight? "cba")))
    (is (nil? (contains-straight? "bac")))))

(deftest password-does-not-contain-invalid-characters
  (testing "Any string that contains an i, j, or o should return false"
    (is (false? (not-contains-invalid-characters? "i")))
    (is (false? (not-contains-invalid-characters? "l")))
    (is (false? (not-contains-invalid-characters? "o")))
    (is (true? (not-contains-invalid-characters? "a")))))

(deftest password-contains-two-non-overlapping-pairs
  (testing "Any string less than four characters should return false"
    (is (false? (contains-non-overlapping-pairs "a")))
    (is (false? (contains-non-overlapping-pairs "aa")))
    (is (false? (contains-non-overlapping-pairs "aaa"))))
  (testing "Must contain two different non-overlapping pairs"
    (is (true? (contains-non-overlapping-pairs "aabb")))
    (is (false? (contains-non-overlapping-pairs "aaaa")))))

(deftest string-to-base26-tests
  (testing "Test all ones place digites"
    (let [mappings {"a" 0, "b" 1, "c" 2, "d" 3, "e" 4, "f" 5, "g" 6, "h" 7, "i" 8, "j" 9, "k" 10, "l" 11, "m" 12, "n" 13, "o" 14, "p" 15, "q" 16, "r" 17, "s" 18, "t" 19, "u" 20, "v" 21, "w" 22, "x" 23, "y" 24, "z" 25}]
      (is (every? (fn [[k v]] (= v (string->base26 k))) mappings))))
  (testing "Sample input from problem statement"
    (is (= 58805701357 (string->base26 "hijklmmn")))
    (is (= 321784924 (string->base26 "abbceffg")))
    (is (= 321785708 (string->base26 "abbcegjk")))))

(deftest base26-to-string-tests
  (testing "Test all ones place digites"
    (let [mappings {"a" 0, "b" 1, "c" 2, "d" 3, "e" 4, "f" 5, "g" 6, "h" 7, "i" 8, "j" 9, "k" 10, "l" 11, "m" 12, "n" 13, "o" 14, "p" 15, "q" 16, "r" 17, "s" 18, "t" 19, "u" 20, "v" 21, "w" 22, "x" 23, "y" 24, "z" 25}]
      (is (every? (fn [[k v]] (= k (base26->string v))) mappings))))
  (testing "Sample input from problem statement, should remove leading a's"
    (is (= "hijklmmn" (base26->string 58805701357)))
    (is (= "bbceffg" (base26->string 321784924)))
    (is (= "bbcegjk" (base26->string 321785708)))))

(deftest generate-passwords-tests
  (testing "Generate passwords from a given string"
    (is (= "bbcdd" (first (generate-passwords 0))))
    (is (= "bcdffaa" (first (generate-passwords (string->base26 "abcdefgh")))))
    (is (= "ghjaabcc" (first (generate-passwords (string->base26 "ghijklmn")))))))
