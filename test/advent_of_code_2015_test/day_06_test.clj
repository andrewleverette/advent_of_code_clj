(ns advent-of-code-2015-test.day-06-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-06 :refer [apply-command
                                                commands-part-1
                                                parse-instruction
                                                process-instructions
                                                project-coordinates
                                                project-command]]))

(deftest coordinate-projection-tests
  (testing "A single coordinate should return itself"
    (is (= '([0 0]) (project-coordinates [0 0] [0 0])))
    (is (= '([0 1]) (project-coordinates [0 1] [0 1])))
    (is (= '([1 0]) (project-coordinates [1 0] [1 0])))
    (is (= '([1 1]) (project-coordinates [1 1] [1 1]))))
  (testing "Two coordinates that project to a line should return the coordinates in the line"
    (is (= '([0 0] [0 1] [0 2]) (project-coordinates [0 0] [0 2])))
    (is (= '([0 0] [1 0] [2 0]) (project-coordinates [0 0] [2 0]))))
  (testing "Two coordinates that project to a rectangle should return the coordinates in the rectangle"
    (is (= '([0 0] [0 1] [1 0] [1 1]) (project-coordinates [0 0] [1 1]))))
  (testing "Starting away from the origin should still return the coordinates in the rectangle"
    (is (= '([0 1] [0 2] [1 1] [1 2]) (project-coordinates [0 1] [1 2])))))

(deftest command-projection-tests
  (testing "A command with the same start and end should return a sequence of that command and coordinate"
    (is (= [[:on [0 0]]] (project-command :on [0 0] [0 0]))))
  (testing "Command project to a line should return the coordinates in the line"
    (is (= [[:on [0 0]] [:on [0 1]] [:on [0 2]]] (project-command :on [0 0] [0 2])))
    (is (= [[:on [0 0]] [:on [1 0]] [:on [2 0]]] (project-command :on [0 0] [2 0]))))
  (testing "Command project to a rectangle should return the coordinates in the rectangle"
    (is (= [[:on [0 0]] [:on [0 1]] [:on [1 0]] [:on [1 1]]] (project-command :on [0 0] [1 1])))))

(deftest command-application-tests
  (testing "Applying a command to a single coordinate should return the grid with the command applied"
    (is (= {[0 0] true} (apply-command commands-part-1 {[0 0] false}  [:on [0 0]])))
    (is (= {[0 0] false} (apply-command commands-part-1 {[0 0] false}  [:off [0 0]])))
    (is (= {[0 0] true} (apply-command commands-part-1 {[0 0] false}  [:toggle [0 0]]))))
  (testing "Applying the same command multiple times should return the same results except for toggle"
    (is (= {[0 0] true} (apply-command commands-part-1 (apply-command commands-part-1 {[0 0] false} [:on [0 0]])  [:on [0 0]])))
    (is (= {[0 0] false} (apply-command commands-part-1 (apply-command commands-part-1 {[0 0] false}  [:off [0 0]])  [:off [0 0]])))
    (is (= {[0 0] false} (apply-command commands-part-1 (apply-command commands-part-1 {[0 0] false}  [:toggle [0 0]])  [:toggle [0 0]])))))

(deftest parsing-instruction-tests
  (testing "Parsing an instruction should return a tuple of command and start and end coordinates"
    (is (= [:on [0 0] [1 1]] (parse-instruction "turn on 0,0 through 1,1")))
    (is (= [:off [0 0] [1 1]] (parse-instruction "turn off 0,0 through 1,1")))
    (is (= [:toggle [0 0] [1 1]] (parse-instruction "toggle 0,0 through 1,1")))))

(deftest process-instructions-test
  (testing "Porcesing a single instructions should return the grid state after the instruction has been applied"
    (is (= {[0 0] true} (process-instructions commands-part-1 [(parse-instruction "turn on 0,0 through 0,0")])))
    (is (= {[0 0] true [0 1] true [0 2] true} (process-instructions commands-part-1 [(parse-instruction "turn on 0,0 through 0,2")])))
    (is (= {[0 0] true [1 0] true [2 0] true} (process-instructions commands-part-1 [(parse-instruction "turn on 0,0 through 2,0")])))
    (is (= {[0 0] true [0 1] true [0 2] true
            [1 0] true [1 1] true [1 2] true
            [2 0] true [2 1] true [2 2] true} (process-instructions commands-part-1 [(parse-instruction "turn on 0,0 through 2,2")]))))
  (testing "Porcesing multiple instructions should return the grid state after all the instructions have been applied"
    (is (= {[0 0] true [0 1] true [0 2] false
            [1 0] true [1 1] true [1 2] false
            [2 0] false [2 1] false [2 2] false} (process-instructions commands-part-1 [(parse-instruction "turn on 0,0 through 2,2")
                                                                                        (parse-instruction "turn off 0,0 through 1,1")
                                                                                        (parse-instruction "toggle 0,0 through 2,2")])))))
