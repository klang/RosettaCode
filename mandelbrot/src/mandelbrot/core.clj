(ns mandelbrot.core
  (:refer-clojure :exclude [+ * <])
  (:use (clojure.contrib complex-numbers)
        (clojure.contrib.generic [arithmetic :only [+ *]]
                                 [comparison :only [<]]
                                 [math-functions :only [abs]])))

(defn mandelbrot?
  ([x y]
     (mandelbrot? (complex x y)))
  ([z]
     (loop [c 1, m (iterate #(+ z (* % %)) z)]
       (if (and (> 64 c) (< (abs (first m)) 2))
	 (recur (inc c)
		(rest m))
	 (if (= 64 c) 0 c)))))


