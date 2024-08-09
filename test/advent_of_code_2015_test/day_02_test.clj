(ns advent-of-code-2015-test.day-02-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-02 :refer [is-valid-cuboid?]]))

(deftest valid-cuboid-test
  (testing "A valid cuboid is is a polyhedron with 6 rectrangular sides where all sides are greater than 0"
    (is (false? (is-valid-cuboid? [0 0 0])))
    (is (false? (is-valid-cuboid? [0 0 1])))
    (is (false? (is-valid-cuboid? [0 1 0])))
    (is (false? (is-valid-cuboid? [0 1 1])))
    (is (false? (is-valid-cuboid? [1 0 0])))
    (is (false? (is-valid-cuboid? [1 0 1])))
    (is (false? (is-valid-cuboid? [1 1 0])))
    (is (true? (is-valid-cuboid? [1 1 1])))))
