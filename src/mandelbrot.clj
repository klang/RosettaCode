(ns mandelbrot
  (:refer-clojure :exclude [+ * <])
  (:use (clojure.contrib complex-numbers)
        (clojure.contrib.generic [arithmetic :only [+ *]]
                                 [comparison :only [<]]
                                 [math-functions :only [abs]])))

(defn- mandelbrot-seq [x y]
  (let [z (complex x y)]
    (iterate #(+ z (* % %)) z)))

(defn mandelbrot? [x y] 
  (loop [c 1
	 m (mandelbrot-seq x y)]
    (if (and (> 20 c) 
	     (< (abs (first m)) 2))
      (recur (inc c) (rest m))
      (if (= 20 c) true false))))

(defn mandelbrot []
  (apply str 
	 (interpose \newline 
		    (for [y (range 1 -1 -0.05)] 
		      (apply str (for [x (range -2 0.5 0.0315)] 
					(if (mandelbrot? x y) "#" " ")))))))

;; submitted to 
;; http://rosettacode.org/wiki/Mandelbrot_set#Clojure
(defn mandelbrot? [z] 
  (loop [c 1
	 m (iterate #(+ z (* % %)) z)]
    (if (and (> 20 c) 
	     (< (abs (first m)) 2) )
      (recur (inc c) 
	     (rest m))
      (if (= 20 c) true false))))


(defn mandelbrot []
  (apply str 
	 (interpose \newline 
		    (for [y (range 1 -1 -0.05)] 
		      (apply str (for [x (range -2 0.5 0.0315)] 
				   (if (mandelbrot? (complex x y)) "#" " ")))))))
(println (mandelbrot))

;;;-----
