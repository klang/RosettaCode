; Transpose an arbitrarily sized rectangular Matrix.

;; added to http://rosettacode.org/wiki/Matrix_Transpose#Clojure

; takes lists
(defn transpose [m]
     (apply map list m))

; takes vectors
(defn transpose [m] 
  (vec (apply map vector m)))

; takes both (and can be extended)
(defmulti transpose class)
(defmethod transpose clojure.lang.PersistentList [m]
  (apply map list m))
(defmethod transpose clojure.lang.PersistentVector [m]
  (vec (apply map vector m)))

(def vm [[3 2 1] 
	 [4 3 2]
	 [5 4 3]])

(def lm '((3 2 1) 
	  (4 3 2)
	  (5 4 3)))

