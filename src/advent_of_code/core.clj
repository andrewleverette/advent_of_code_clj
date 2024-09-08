(ns advent-of-code.core
  (:require

   [clojure.string :as string]
   [clojure.tools.cli :as cli]
   [clojure.java.io :as io]

   [advent-of-code-2015.core :as aoc-2015]))

(defn read-puzzle-input
  "Given a year and a day as strings, return the puzzle input as a string"
  [year day]
  (-> (format "puzzle_input/%s/day%02d.txt" year (Integer/parseInt day))
      io/resource
      slurp
      string/trim))

(defn usage
  "Prints the usage information"
  [options-summary]
  (->> ["This is a tool for runing Advent of Code puzzle solutions."
        ""
        "Usage: advent-of-code [options]"
        ""
        "Options:"
        options-summary]
       (string/join \newline)))

(def cli-opts
  [["-y" "--year YEAR" "The year of the Advent of Code event"]
   ["-d" "--day DAY" "The day of the Advent of Code event"]
   ["-p" "--part PART" "The part of the puzzle to solve"]
   ["-h" "--help"]])

(defn validate-args
  [args]
  (let [{:keys [options errors summary]} (cli/parse-opts args cli-opts)]
    (cond
      (:help options) {:exit-message (usage summary)}
      errors {:exit-message (string/join \newline errors)}
      (not (and (:year options) (:day options) (:part options))) {:exit-message (usage summary)}
      :else {:options options})))

(defn get-aoc-runner
  "Given a year as a string, return the corresponding runner function"
  [year]
  (case year
    "2015" aoc-2015/runner
    (throw (UnsupportedOperationException. (str "Unsupported year: " year)))))

(defn output
  [year day part result]
  (println (format "================================
Year: %s
Day:  %s
Part:  %s
Result:  %s
================================"
                   year day part result)))

(defn -main
  [& args]
  (let [{:keys [options exit-message]} (validate-args args)]
    (if exit-message
      (println exit-message)
      (let [{:keys [year day part]} options
            puzzle-input (read-puzzle-input year day)
            day-keyword (keyword (str "day" day "." part))
            runner (get-aoc-runner year)]
        (->> puzzle-input (runner day-keyword) (output year day part))))))
