(ns advent-of-code-2015-test.day-07-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2015.day-07 :refer [parse-instruction
                                                reduce-exp]]))

(deftest instruction-parsing
  (testing "An empty instruction should return nil"
    (is (nil? (parse-instruction ""))))
  (testing "An invalid instruction should return nil"
    (is (nil? (parse-instruction "this is invalid")))
    (is (nil? (parse-instruction "123 <- x")))
    (is (nil? (parse-instruction "123 => x"))))
  (testing "Parsing a valid instruction should return a tuple of binding value and expression value"
    (is (= [:x {:op :signal
                :args [123]
                :output :x}] (parse-instruction "123 -> x")))
    (is (= [:y {:op :signal
                :args [456]
                :output :y}] (parse-instruction "456 -> y")))
    (is (= [:h {:op :not
                :args [:x]
                :output :h}] (parse-instruction "NOT x -> h")))
    (is (= [:d {:op :and
                :args [:x :y]
                :output :d}] (parse-instruction "x AND y -> d")))
    (is (= [:e {:op :or
                :args [:x :y]
                :output :e}] (parse-instruction "x OR y -> e")))
    (is (= [:f {:op :lshift
                :args [:x 2]
                :output :f}] (parse-instruction "x LSHIFT 2 -> f")))
    (is (= [:g {:op :rshift
                :args [:y 2]
                :output :g}] (parse-instruction "y RSHIFT 2 -> g")))))

(deftest expression-reduction
  (testing "Signal expressions are reduced to integers when possible"
    (is (= {:x 123} (reduce-exp {} {:op :signal
                                    :args [123]
                                    :output :x})))
    (is (= {:x 123
            :y 123} (reduce-exp {:x 123} {:op :signal
                                          :args [:x]
                                          :output :y})))
    (is (= {:x {:op :signal
                :args [:y]
                :output :x}} (reduce-exp {:x {:op :signal
                                              :args [:y]
                                              :output :x}} {:op :signal
                                                            :args [:y]
                                                            :output :x}))))
  (testing "Unary expressions are reduced to integers when possible"
    (let [simple-unary {:x 123
                        :y {:op :not
                            :args [:x]
                            :output :y}}
          complex-unary {:x {:op :signal
                             :args [:y]
                             :output :x}
                         :z {:op :not
                             :args [:x]
                             :output :z}}]
      (is (= {:x 123
              :y -124} (reduce-exp simple-unary (simple-unary :y))))
      (is (= complex-unary (reduce-exp complex-unary (complex-unary :z))))))
  (testing "Binary expressions are reduced to integers when possible"
    (let [simple-binary {:x 123
                         :y 456
                         :z {:op :and
                             :args [:x :y]
                             :output :z}}
          complex-binary {:x 123
                          :y {:op :signal
                              :args [:d]
                              :output :y}
                          :z {:op :or
                              :args [:x :y]
                              :output :z}}]
      (is (= {:x 123
              :y 456
              :z 72} (reduce-exp simple-binary (simple-binary :z))))
      (is (= {:x 123
              :y {:op :signal
                  :args [:d]
                  :output :y}
              :z {:op :or
                  :args [123 :y]
                  :output :z}} (reduce-exp complex-binary (complex-binary :z)))))))
