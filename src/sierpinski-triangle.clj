(ns rosettacode
  (:use clojure.contrib.math)
  (:use helpers) )

(defn sierpinski-triangle [order]
  (loop [lines []
	 size (expt 2 order)
	 v    (expt 2 (- size 1))]
    (if (zero? size)
      (println (apply str (interpose \newline lines)))
      (recur (conj lines (apply str (vec 
				     (map #(if (bit-test v %) "*" " ") 
					  (range (integer-length v)))))) 
	     (dec size) 
	     (bit-xor (bit-shift-left v 1) (bit-shift-right v 1))))))

(comment
(sierpinski-triangle 4)
) 
