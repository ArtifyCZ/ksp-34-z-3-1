(ns ksp-34-3z-1.core
  (:require [clojure.string :as str]))

(defn read-input [num]
  (->> (slurp (str "data/input" num ".in"))
       str/split-lines
       rest))

(defn read-alphabet []
  (as-> (slurp "data/alphabet.txt") data
        (str/replace data #"(\ |\n|\r|\")" "")
        (str/split data #"\,")
        (map #(-> % (str/split #"\:")
                  (update 0 first)) data)
        (into {} data)))

(defn as-morse-code [ s alphabet ]
  (->> s
       (map alphabet)
       (apply str)))

(defn morse-code-pali? [ s alphabet ]
  (let [ s2 (as-morse-code s alphabet)
        s3 (str/reverse s2) ]
    (= s2 s3)))

(defn -main [ num ]
  (let [ alphabet (read-alphabet)
        input (read-input num) ]
    (->> input
         (map #(if (morse-code-pali? % alphabet)
                 "ANO" "NE"))
         (str/join "\n")
         (#(str % "\n"))
         (spit (str "data/output" num ".out")))))
