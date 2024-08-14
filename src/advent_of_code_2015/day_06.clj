(ns advent-of-code-2015.day-06
  "--- Day 6: Probably a Fire Hazard ---
  https://adventofcode.com/2015/day/6"
  (:require [advent-of-code.utils :as utils]))

;; This command map is for part 1
(def commands-part-1 {:on (constantly true)
                      :off (constantly false)
                      :toggle not})

;; This command map is for part 2
(def commands-part-2 {:on (fnil inc 0)
                      :off (fnil (comp (partial max 0) dec) 0)
                      :toggle (fnil (partial + 2) 0)})

(defn project-coordinates
  "Given two coordinates, return a sequence of coordinates
  in the rectangular space between them."
  [[x1 y1] [x2 y2]]
  (for [x (range x1 (inc x2))
        y (range y1 (inc y2))]
    [x y]))

(defn project-command
  "Given a command and two coordinates, return a sequence
  of pairs of the command and coordinates in the rectangular space between them."
  [command start end]
  (map (fn [coord] [command coord]) (project-coordinates start end)))

(defn apply-command
  "Given a command map, a grid, and a tuple of comman and coordinate, 
  return the grid with the command applied to the coordinate."
  [commands grid [command coord]]
  (let [action (get commands command identity)]
    (update grid coord action)))

(defn parse-command-str
  [command-str]
  (condp = command-str
    "turn on" :on
    "turn off" :off
    "toggle" :toggle))

(defn parse-instruction
  "Given a string, return a tuple of the command and the start and end coordinates."
  [input]
  (let [[_ command x1 y1 x2 y2] (re-matches #"(turn on|turn off|toggle) (\d+),(\d+) through (\d+),(\d+)" input)]
    [(parse-command-str command)
     [(Integer/parseInt x1) (Integer/parseInt y1)]
     [(Integer/parseInt x2) (Integer/parseInt y2)]]))

(defn process-instructions
  "Given a command map and a sequence of instructions, 
  return the grid state after all the instructions have been applied."
  [commands instructions]
  (->> instructions
       (mapcat (fn [[command start end]] (project-command command start end)))
       (reduce (partial apply-command commands) {})))

(defn part-1
  [input]
  (->> input
       utils/parse-input
       (map parse-instruction)
       (process-instructions commands-part-1)
       vals
       (filter true?)
       count))

(defn part-2
  [input]
  (->> input
       utils/parse-input
       (map parse-instruction)
       (process-instructions commands-part-2)
       vals
       (reduce +)))
