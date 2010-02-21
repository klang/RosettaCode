;; http://rosettacode.org/wiki/Sum_of_squares#Clojure
(defn sum-of-squares [v]
  (reduce #(+ %1 (* %2 %2)) 0 v))