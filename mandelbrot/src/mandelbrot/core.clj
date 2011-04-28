(ns mandelbrot.core
  (:refer-clojure :exclude [+ * <])
  (:use (clojure.contrib complex-numbers
			 [seq-utils :only (indexed)])
        (clojure.contrib.generic [arithmetic :only [+ *]]
                                 [comparison :only [<]]
                                 [math-functions :only [abs]])))

;; submitted to 
;; http://rosettacode.org/wiki/Mandelbrot_set#Clojure
(defn mandelbrot? [z] 
  (loop [c 1, m (iterate #(+ z (* % %)) z)]
    (if (and (> 20 c) (< (abs (first m)) 2) )
      (recur (inc c) (rest m))
      (if (= 20 c) true false))))

;; the ranges are selected specifically to give a 80x40 ascii picture
;; the ordering of y and x in the for loop results in top to bottom printing
(defn mandelbrot []
  (for [y (range 1 -1 -0.05)
	x (range -2 0.5 0.0315)] 
    (if (mandelbrot? (complex x y)) "#" " ")))

(comment
  ;; as we know the length of each line, we can partition the sequence
  ;; delivered by (mandelbrot)
  (doseq [line (map #(apply str %) (partition 80 (mandelbrot)))] (println line))
  ;; mandelbrot-ascii.png
  )

;;;----- printed in a java.awt.Frame

;(defonce frame (java.awt.Frame.))

;(.setVisible frame true)
;(.setSize frame (java.awt.Dimension. (+ 250 3) (+ 200 23)))

(defonce frame
  (doto (java.awt.Frame.)
    (.setVisible true)
    (.setSize (java.awt.Dimension. (+ 250 3) (+ 200 23)))))

(defonce gfx (.getGraphics frame))

(defn- clear [g] (.clearRect g 0 0 (+ 250 3) (+ 200 23)))

(defn- plot [[x y] [r g b]]
  (doto gfx
    (.setColor (java.awt.Color. r g b))
    (.fillRect x y 1 1)))

(comment
  (plot [100 100] [255 0 0])
  (clear gfx)
  )

(defn- step
  "retuns the step size when 'steps' are wanted from 'start' to end'."
  [start end steps]
  (float (/ (- end start) steps )))

(defn- stepped-range
  "retuns a range from start to finish divided in steps."
  [start end steps]
  (range start end (step start end steps)))

(defn- transform
  "move all coordinates a bit, so the entire drawing is visible"
  [x y]
  (let [menu-bar-height 23
	border-width     3]
    [(+ x border-width)
     (+ y menu-bar-height)]))

(defn mandelbrot [width height]
  (for [x (indexed (stepped-range -2  0.5 width))
	y (indexed (stepped-range  1 -1   height))]
    [(first x) (first y) (if (mandelbrot? (complex (second x) (second y))) 0 255)]))

(comment
  (clear gfx)
  (doseq [[x y v] (mandelbrot 250 200)] (plot (transform x y) [v v v])))

(defn draw-mandelbrot [width height]
  (let [menu-bar-height 23
	border-width     3]
    (.clearRect gfx 0 0 (+ width border-width) (+ height menu-bar-height))
    (.setSize frame (java.awt.Dimension. (+ width border-width) (+ height menu-bar-height)))
    (doseq [[x y v] (mandelbrot width height)]
      (plot (transform x y) [v v v]))))

(comment
  (draw-mandelbrot 250 200)
  ;; mandelbrot-bw.png
  )

;;; -- with colors

;; return the number of iterations used or 0 if the limit was passed
(defn mandelbrot? [z]
  (loop [c 1, m (iterate #(+ z (* % %)) z)]
    (if (and (> 64 c) (< (abs (first m)) 2) )
      (recur (inc c) 
	     (rest m))
      (if (= 64 c) 0 c))))

(defn mandelbrot [width height]
  (for [x (indexed (stepped-range -2  0.5 width))
	y (indexed (stepped-range  1 -1   height))]
    [(first x) (first y) (rem (mandelbrot? (complex (second x) (second y))) 256)]))

(comment
  (draw-mandelbrot 250 200)
  ;; mandelbrot-shaded.png
  )

;; for the small pictures we are making, and the number of iterations we have
;; chosen, only the first 64 colors are used. 
(def colors
     (assoc
	 (into {}
	       (for [n (range 0 64) :let [v (* n 4)]]
		 {(+   0 n) [(rem (+ v 128) 256) (* n 4) 0]
		  (+  64 n) [64 255 v]
		  (+ 128 n) [64 (- 255 v) 255]
		  (+ 192 n) [64 0 (- 255 v)]}))
       0 [0 0 0]))

(defn draw-mandelbrot [width height]
  (let [menu-bar-height 23
	border-width     3]
    (.clearRect gfx 0 0 (+ width border-width) (+ height menu-bar-height))
    (.setSize frame (java.awt.Dimension. (+ width border-width) (+ height menu-bar-height)))
    (doseq [[x y v] (mandelbrot width height)]
      (plot (transform x y) (colors v)))))

(comment
  (draw-mandelbrot 250 200)
  ;; mandelbrot-color.png
  )



