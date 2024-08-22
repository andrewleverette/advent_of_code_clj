(ns advent-of-code-2015.day-07
  "--- Day 7: Some Assembly Required ---
  https://adventofcode.com/2015/day/7"
  (:require [advent-of-code.utils :as utils]))

(def matchers
  "Regexes for parsing instructions"
  [#"(\w+) -> (\w+)"
   #"NOT (\w+) -> (\w+)"
   #"(\w+) (AND|OR|LSHIFT|RSHIFT) (\w+) -> (\w+)"])

(defn parse-exp
  "Parse exprsession into integers or keywords"
  [coll]
  (map (fn [x] (if (integer? x) x (keyword x))) coll))

(defn parse-unary-exp
  "Parse an unary expresssion. The only unary operator is NOT"
  [command]
  (parse-exp (rest command)))

(defn parse-binary-exp
  "Parse an binary expression. The form of the comman is '[x OP y]'"
  [command]
  (parse-exp ((juxt first last) command)))

(defn command->node
  "Parse command into a node given the operator, command and output target"
  [op command output]
  (condp = op
    'NOT [output {:op :not
                  :args (parse-unary-exp command)
                  :output output}]
    'AND [output {:op :and
                  :args (parse-binary-exp command)
                  :output output}]
    'OR [output {:op :or
                 :args (parse-binary-exp command)
                 :output output}]
    'LSHIFT [output {:op :lshift
                     :args (parse-binary-exp command)
                     :output output}]
    'RSHIFT [output {:op :rshift
                     :args (parse-binary-exp command)
                     :output output}]
    [output {:op :signal
             :args (parse-exp command)
             :output output}]))

(defn parse-instruction
  "Parse an instruction into a node"
  [instruction]
  (when-some [_ (some (fn [matcher] (re-matches matcher instruction)) matchers)]
    (let [tokens (read-string (str "[" instruction "]"))
          output (keyword (last tokens))
          command (take-while #(not= '-> %) tokens)
          op (first (filter #(re-matches #"(NOT|AND|OR|LSHIFT|RSHIFT)" (str %)) command))]
      (command->node op command output))))

(defn init-source
  "Reduce collection of nodes into a map"
  [nodes]
  (reduce (fn [source [k v]] (assoc source k v)) {} nodes))

(defn value [source k]
  (if (or (integer? k) (not (integer? (source k))))
    k
    (source k)))

(defn reduce-exp
  "Reduce an expression into a value, a partial expression, or leave the expression unchanged"
  [source {:keys [op args output]}]
  (let [reduced-args (map (partial value source) args)]
    (if (every? integer? reduced-args)
      (let [value (condp = op
                    :signal (first reduced-args)
                    :not (bit-not (first reduced-args))
                    :and (apply bit-and reduced-args)
                    :or (apply bit-or reduced-args)
                    :lshift (apply bit-shift-left reduced-args)
                    :rshift (apply bit-shift-right reduced-args))]
        (assoc source output value))
      (update-in source [output :args] (constantly reduced-args)))))

(defn next-source-state
  "Reduce the source state to the next state"
  [source]
  (->> source
       vals
       (filter (complement integer?))
       (reduce reduce-exp source)))

(defn solve
  "Solve for the given key in the source state"
  [k source]
  (->> source
       (iterate next-source-state)
       (filter #(integer? (% k)))
       first
       k))

(defn part-1
  [input]
  (->> input
       (utils/parse-input parse-instruction)
       init-source
       (solve :a)))

(defn part-2
  [input]
  (let [source (->> input (utils/parse-input parse-instruction) init-source)
        signal-a (solve :a source)
        overridden-source (assoc source :b signal-a)]
    (solve :a overridden-source)))
