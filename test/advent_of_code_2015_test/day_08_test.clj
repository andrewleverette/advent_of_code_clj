(ns advent-of-code-2015-test.day-08-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-08 :refer [calculate-character-difference
                                                encode-string
                                                encoded-string-length
                                                replace-escape-sequences
                                                in-memory-length]]))

(deftest replace-escape-sequences-tests
  (testing "An empty string should return unchanged"
    (is (= "" (replace-escape-sequences ""))))
  (testing "A string with no escaped characters should return unchanged"
    (is (= "a" (replace-escape-sequences "a")))
    (is (= "abc" (replace-escape-sequences "abc"))))
  (testing "A string with an escaped character should return with '.' as the replaced character"
    (is (= "a.b" (replace-escape-sequences "a\\\"b")))
    (is (= "a.b" (replace-escape-sequences "a\\\\b")))
    (is (= "a.b" (replace-escape-sequences "a\\x27b")))))

(deftest in-memeory-length-tests
  (testing "An empty string should return -2 to offset the quote count"
    (is (= -2 (in-memory-length ""))))
  (testing "A string with no escaped characters should return the length of the original string minus 2"
    (is (= -1 (in-memory-length "a")))
    (is (= 1 (in-memory-length "abc"))))
  (testing "A string with an escaped character should return the length of the encoded string minus 2"
    (is (= 1 (in-memory-length "a\\\"b")))
    (is (= 1 (in-memory-length "a\\\\b")))
    (is (= 1 (in-memory-length "a\\x27b")))))

(deftest encode-string-tests
  (testing "An empty string should return an empty string"
    (is (= "" (encode-string ""))))
  (testing "A string with no escaped characters should return unchanged"
    (is (= "a" (encode-string "a")))
    (is (= "abc" (encode-string "abc"))))
  (testing "A string with an escaped character should return with '..' as the replaced character"
    (is (= "a..b" (encode-string "a\"b")))
    (is (= "a..b" (encode-string "a\\b")))
    (is (= "a..x27b" (encode-string "a\\x27b")))))

(deftest encoded-string-length-tests
  (testing "An empty string should return 2 to offset the quote count"
    (is (= 2 (encoded-string-length ""))))
  (testing "A string with no escaped characters should return the length of the original string plus 2"
    (is (= 3 (encoded-string-length "a")))
    (is (= 5 (encoded-string-length "abc"))))
  (testing "A string with an escaped character should return the length of the encoded string plus 2"
    (is (= 6 (encoded-string-length "a..b")))
    (is (= 9 (encoded-string-length "a..x27b")))))
