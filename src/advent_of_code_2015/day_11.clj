(ns advent-of-code-2015.day-11
  "--- Day 11: Corporate Policy ---
 https://adventofcode.com/2015/day/11")

(defn contains-straight?
  "Given a string, return true if the string contains at least 
  three sequential letters in increasing order."
  [s]
  (->> s
       (map byte)
       (partition 3 1)
       (some (fn [[a b c]] (= (+ 2 a) (inc b) c)))))

(defn not-contains-invalid-characters?
  "Given a string, return true if the string does not contain any
  of the following characters: i, j, o."
  [s]
  (nil? (re-find #"[iol]" s)))

(defn contains-non-overlapping-pairs
  "Given a string, return true if the string contains two
  different non-overlapping pairs."
  [s]
  (->> s
       (partition 2 1)
       (filter #(apply = %))
       set
       count
       (< 1)))

(def is-valid-password? (every-pred not-contains-invalid-characters?
                                    contains-non-overlapping-pairs
                                    contains-straight?))

(defn base26->char
  "Conert a base26 encoded byte into alphabetic character."
  [x]
  (char (+
         (byte x)
         (if (<= (byte x) (byte \9)) 49 10))))

(defn char->base26
  "Convert an alphabetic character into a base26 encoded byte."
  [x]
  (char (-
         (byte x)
         (if (<= (byte x) (byte \j)) 49 10))))

(defn base26-encode
  "Given a string s, return a base26 encoded string."
  [s]
  (apply str (map char->base26 s)))

(defn base26-decode
  "Given a base26 encoded string, return the original string."
  [s]
  (apply str (map base26->char s)))

(defn base26->string
  "Given an integer i that was encoded with base26, return a decoded alphabetic string that represents i."
  [i]
  (base26-decode (java.lang.Long/toString i 26)))

(defn string->base26
  "Given a string, return an integer that represents the base26 encoded string."
  [s]
  (java.lang.Long/parseLong (base26-encode s) 26))

(defn generate-passwords
  "Given a starting integer, generate a lazy sequence of valid passwords"
  [start]
  (->> start
       (iterate inc)
       (map base26->string)
       (filter is-valid-password?)))

(defn part-1
  [input]
  (->> input
       string->base26
       generate-passwords
       first))

(defn part-2
  [input]
  (->> input
       string->base26
       generate-passwords
       second))
