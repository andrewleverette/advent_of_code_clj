(ns advent-of-code-2015.day-02
  "--- 2015 Day 2: I Was Told There Would Be No Math --
  https://adventofcode.com/2015/day/2"
  (:require [clojure.string :as str]))

(defn area
  "Calculate the area of a rectangle given a tuple of length and width."
  [[l w]]
  (* l w))

(defn perimeter
  "Calculate the perimeter of a rectangle given a tuple of length and width."
  [[l w]]
  (* 2 (+ l w)))

(defn volume
  "Calculate the volume of a cuboid given a tuple of length, width, and height."
  [[l w h]]
  (* l w h))

(defn surface-area
  "Calculate the surface area of a cuboid given a tuple of length, width, and height."
  [[l w h]]
  (* 2 (+ (* l w) (* l h) (* w h))))

(defn is-valid-cuboid?
  "Takes a cuboid and returns true if the cuboid is valid.
  A cuboid is valid if all dimensions are positive."
  [cuboid]
  (every? pos? cuboid))

(defn str->cuboid
  "Takes a string and returns a cuboid vector."
  [s]
  (->> (str/split s #"x")
       (map #(Integer/parseInt %))
       vec))

(defn smallest-side
  "Takes a cuboid and returns its smallest side."
  [[l w h]]
  (let [a (min l w h)
        c (max l w h)
        b (- (+ l w h) c a)]
    [a b]))

(defn total-required-footage
  "Takes a function and a list of dimensions and returns the total required footage."
  [f dimensions]
  (->> dimensions
       (map f)
       (reduce +)))

(defn wrapping-paper-for-present
  "Takes a the dimensions of a present and returns the required wrapping paper."
  [dimension]
  (+ (-> dimension smallest-side area)
     (surface-area dimension)))

(defn total-square-feet-of-wrapping-paper
  "Takes a list of dimensions and returns the total square feet of wrapping paper required."
  [dimensions]
  (total-required-footage wrapping-paper-for-present dimensions))

(defn ribbon-for-present
  "Takes a the dimensions of a present and returns the required ribbon."
  [dimension]
  (+ (-> dimension smallest-side perimeter)
     (volume dimension)))

(defn total-feet-of-ribbon
  "Takes a list of dimensions and returns the total feet of ribbon required."
  [dimension]
  (total-required-footage ribbon-for-present dimension))

(defn parse-input
  "Takes a string and returns a list of cuboids."
  [input]
  (->> input
       (str/split-lines)
       (map str->cuboid)))

(defn part-1
  [input]
  (->> input
       parse-input
       total-square-feet-of-wrapping-paper))

(defn part-2
  [input]
  (->> input
       parse-input
       total-feet-of-ribbon))
