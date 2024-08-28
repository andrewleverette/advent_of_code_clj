(ns advent-of-code-2015-test.day-09-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-09 :refer [parse-connection
                                                vertices
                                                distance
                                                distances]]))

(deftest parse-connection-tests
  (testing "Convert a line into the form [#{start end} distance]"
    (is (= [#{:a :b} 1] (parse-connection "a to b = 1")))
    (is (= [#{:london :dublin} 464] (parse-connection "London to Dublin = 464")))
    (is (= [#{:dublin :belfast} 141] (parse-connection "Dublin to Belfast = 141")))))

(deftest get-vertices-of-graph
  (testing "An empty graph should return an empty set"
    (is (= #{} (vertices {}))))
  (testing "Get all the vertices in the graph as a set"
    (is (= #{:a :b} (vertices {#{:a :b} 1})))
    (is (= #{:london :dublin} (vertices {#{:london :dublin} 464})))
    (is (= #{:dublin :belfast} (vertices {#{:dublin :belfast} 141})))))

(deftest distance-travelled-along-path
  (testing "An empty path should return 0"
    (is (= 0 (distance {} []))))
  (testing "A path of one vertex should return 0"
    (is (= 0 (distance {#{:a :b} 1} [:a]))))
  (testing "A path of two vertices should return the edge"
    (is (= 1 (distance {#{:a :b} 1} [:a :b])))
    (is (= 464 (distance {#{:london :dublin} 464} [:london :dublin]))))
  (testing "A path of three or more vertices should return the sum of the edges"
    (let [graph {#{:london :dublin} 464
                 #{:london :belfast} 518
                 #{:dublin :belfast} 141}]
      (is (= 982 (distance graph [:dublin :london :belfast])))
      (is (= 659 (distance graph [:london :belfast :dublin])))
      (is (= 605 (distance graph [:london :dublin :belfast]))))))

(deftest distances-travelled-of-all-possible-paths
  (testing "An empty graph should return a set with zero"
    (is (= #{0} (distances {}))))
  (testing "Distances of all possible paths"
    (let [graph {#{:london :dublin} 464
                 #{:london :belfast} 518
                 #{:dublin :belfast} 141}]
      (is (= #{982 605 659} (distances graph))))))
