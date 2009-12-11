(ns sierpinski
  (:use clojure.contrib.math)
  (:gen-class))

;; Produce an ASCII representation of a Sierpinski triangle of order N. 
;; For example, the Sierpinski triangle of order 4 should look like this:

;;                *
;;               * *
;;              *   *
;;             * * * *
;;            *       *
;;           * *     * *
;;          *   *   *   *
;;         * * * * * * * *
;;        *               *
;;       * *             * *
;;      *   *           *   *
;;     * * * *         * * * *
;;    *       *       *       *
;;   * *     * *     * *     * *
;;  *   *   *   *   *   *   *   *
;; * * * * * * * * * * * * * * * *

;; stolen method:
;; integer-length defined as a private function in clojure.contrib.math
; Length of integer in binary, used as helper function for sqrt.
(defmulti #^{:private true} integer-length class)
(defmethod integer-length java.lang.Integer [n]
  (count (Integer/toBinaryString n)))
(defmethod integer-length java.lang.Long [n]
  (count (Long/toBinaryString n)))
(defmethod integer-length java.math.BigInteger [n]
  (count (. n toString 2)))

;; the version for http://rosettacode.org/wiki/Sierpinski_triangle#Clojure
;; print out the result as we go
(defn sierpinski-triangle [order]
  (loop [side-effect true
	 size (expt 2 order)
	 v    (expt 2 (- size 1))]
    (if (not (zero? size))
      (recur (println (apply str (map #(if (bit-test v %) "*" " ") 
	     (range (integer-length v))))) 
	     (dec size) 
	     (bit-xor (bit-shift-left v 1) (bit-shift-right v 1))))))

;; lisp     clojure
;; ash      bit-shift-left
;; logbitp  bit-test
;; printc   print

;;
(defn line [v]
  (apply str (vec 
	(map #(if (bit-test v %) "*" " ") 
	     (range 0 (integer-length v) 1)))))

;; print out the result as we go
(defn sierpinski1 [order]
  (loop [side-effect true
	 size (expt 2 order)
	 v    (expt 2 (- size 1))]
    (if (not (zero? size))
      (recur (println (line v)) 
	     (dec size) 
	     (bit-xor (bit-shift-left v 1) (bit-shift-right v 1))))))

;; collect the result and print it out last
(defn sierpinski2 [order]
  (loop [lines []
	 size (expt 2 order)
	 v    (expt 2 (- size 1))]
    (if (not (zero? size))
      (recur (conj lines (line v)) 
	     (dec size) 
	     (bit-xor (bit-shift-left v 1) (bit-shift-right v 1)))
      (println (apply str (interpose \newline lines))))
    )
  )

(defn -main [& args]
  (if args
    (sierpinski1 (. Integer parseInt (first args) 10))
    (sierpinski1 4))
  )
