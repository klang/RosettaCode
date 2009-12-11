(ns rosettacode
  (:use clojure.contrib.math))
;; 
;; ###########################
;; # ## ## ## ## ## ## ## ## #
;; ###########################
;; ###   ######   ######   ###
;; # #   # ## #   # ## #   # #
;; ###   ######   ######   ###
;; ###########################
;; # ## ## ## ## ## ## ## ## #
;; ###########################
;; #########         #########
;; # ## ## #         # ## ## #
;; #########         #########
;; ###   ###         ###   ###
;; # #   # #         # #   # #
;; ###   ###         ###   ###
;; #########         #########
;; # ## ## #         # ## ## #
;; #########         #########
;; ###########################
;; # ## ## ## ## ## ## ## ## #
;; ###########################
;; ###   ######   ######   ###
;; # #   # ## #   # ## #   # #
;; ###   ######   ######   ###
;; ###########################
;; # ## ## ## ## ## ## ## ## #
;; ###########################

;; development process kept for confusion 

(defn sierpinski-carpet [order] nil)

(defn in-carpet? [x y]
  true
)

(defn carpet [order]
  (let [size (expt 3 order)]
    (for [i (range size)] 
      (for [j (range size)] 
	(if (in-carpet? i j) "*" " ")))))

(defn carpet [n]
  (for [i (range (expt 3 n))] 
    (for [j (range (expt 3 n))] 
      (if (in-carpet? i j) "*" " "))))

(defn jline [i n]
  (println (apply str (vec (map #(if (in-carpet? i %) "*" " ") (range (expt 3 n)))))))

(defn carpet [n]
  (for [i (range (expt 3 n))] 
    (jline i n)))

(defn iline [n] 
  (println (apply str (vec (map #(jline % n) (range (expt 3 n)))))))

(defn carpet [n]
  (iline n))

(defn carpet [n]
  (println (apply str (vec (map #(jline % n) (range (expt 3 n))))))
  )


(defn in-carpet? [x y]
  (loop [x x
	 y y]
    (cond (or (zero? x) (zero? y) true)
	  (and (= 1 (mod x 3)) (= 1 (mod y 3)))
	  :else nil)
    )
)

(defn a [x y] (or (zero? x) (zero? y)))
(defn b [x y] (and (= 1 (mod x 3)) (= 1 (mod y 3))))

(defn mytest [x y]
  (cond (a x y) true
	(b x y) false
	))

(defn in-carpet? [x y]
  (loop [x x
	 y y]
    (cond (a x y) true
	  (b x y) false
	  :else (recur (quot x 3) (quot y 3))
	)
    )
)

;; final (based on scheme version)
;; this version is submitted to RosettaCode.org
;; http://rosettacode.org/wiki/Sierpinski_carpet#Clojure
(defn in-carpet? [x y]
  (loop [x x, y y]
    (cond (or (zero? x) (zero? y)) 
	  true
	  (and (= 1 (mod x 3)) (= 1 (mod y 3))) 
	  false
	  :else 
	  (recur (quot x 3) (quot y 3)))))

;; could be better
(defn sierpinski-carpet [n]
  (letfn [(jline [i n]
		 (println 
		  (apply str (vec 
			      (map #(if (in-carpet? i %) "*" " ") 
				   (range (expt 3 n)))))))
	  (iline [n] 
		 (println 
		  (apply str (vec 
			      (map #(jline % n) 
				   (range (expt 3 n)))))))]
    (iline n)))

(use '[clojure.contrib.str-utils :only (str-join)])

(defn carpet [n]
  (apply str 
	 (interpose \newline 
		    (for [i (range (expt 3 n))] 
		      (apply str (vec (for [j (range (expt 3 n))] 
					(if (in-carpet? i j) "*" " "))))))))

;; this version is submitted to RosettaCode.org (with in-carpet? above)
;; http://rosettacode.org/wiki/Sierpinski_carpet#Clojure
(defn carpet [n]
  (apply str 
	 (interpose \newline 
		    (for [i (range (expt 3 n))] 
		      (apply str (for [j (range (expt 3 n))] 
					(if (in-carpet? i j) "*" " ")))))))

(use '[clojure.contrib.str-utils :only (str-join)])
;; "(apply str (interpose" is better written as "str-join"
(defn carpet [n]
  (str-join \newline 
	    (for [i (range (expt 3 n))] 
	      (str-join "" (for [j (range (expt 3 n))] 
				(if (in-carpet? i j) "*" " "))))
	 ))

;;-----------------------------------------------------------------------------
;; final (based on scheme version)
;; this version is submitted to RosettaCode.org
;; http://rosettacode.org/wiki/Sierpinski_carpet#Clojure
(defn in-carpet? [x y]
  (loop [x x, y y]
    (cond (or (zero? x) (zero? y)) 
	  true
	  (and (= 1 (mod x 3)) (= 1 (mod y 3))) 
	  false
	  :else 
	  (recur (quot x 3) (quot y 3)))))

(defn sierpinski-carpet [n]
  (apply str 
	 (interpose \newline 
		    (for [i (range (expt 3 n))] 
		      (apply str (for [j (range (expt 3 n))] 
					(if (in-carpet? i j) "*" " ")))))))

(print sierpinski-carpet 3)
;;-----------------------------------------------------------------------------