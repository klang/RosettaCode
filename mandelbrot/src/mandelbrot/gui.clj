(ns mandelbrot.gui
  (:use (mandelbrot [core :only (mandelbrot?)])
	(clojure.contrib [seq-utils :only (indexed)]))
  (:import (java.awt Frame Dimension Color)
	   (java.awt.event WindowEvent WindowAdapter)))

(defonce frame
  (doto (Frame.)
    (.setVisible true)
    (.setSize (Dimension. (+ 250 3) (+ 200 23)))
    (.addWindowListener
	(proxy [WindowAdapter] []
	  (windowClosing [e] (.dispose frame))))))

(defonce gfx (.getGraphics frame))

(defn- clear [g] (.clearRect g 0 0 (+ 250 3) (+ 200 23)))

(defn- plot [[x y] [r g b]]
  (doto gfx
    (.setColor (Color. r g b))
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

;;; -- with colors

;; return the number of iterations used or 0 if the limit was passed


(defn mandelbrot [width height]
  (for [x (indexed (stepped-range -2  0.5 width))
	y (indexed (stepped-range  1 -1   height))]
    [(first x) (first y) (rem (mandelbrot? (second x) (second y)) 256)]))

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
    (.setSize frame (Dimension. (+ width border-width) (+ height menu-bar-height)))
    #_(doseq [[x y v] (mandelbrot width height)]
      (plot (transform x y) (colors v)))
    #_(dorun (map (fn [[x y v]] (plot (transform x y) (colors v)))
		(mandelbrot width height)))
    (dorun (pmap (fn [[x y v]] (plot (transform x y) (colors v)))
		(mandelbrot width height)))))


(comment
  (draw-mandelbrot 250 200)
  ;; mandelbrot-color.png
  )

;; a different strategy is to separate the production of the canvas points from the
;; actual calculation, but it is just that; a different equivalent strategy

(defn mandelbrot-points
  "returns the coordinate points for each relative point within a with x height canvas"
  [width height]
  (for [x (indexed (stepped-range -2  0.5 width))
	y (indexed (stepped-range  1 -1   height))]
    {:x (second x) :y (second y)
     :rx (first x) :ry (first y)}))

(defn mplot
  "returns the relative coordinate and the color value for the plot"
  [{:keys [x y rx ry]}]
  (plot (transform rx ry) (colors (rem (mandelbrot? x y) 256))))

(defn draw-mandelbrot [width height]
  (let [menu-bar-height 23
	border-width     3]
    (.clearRect gfx 0 0 (+ width border-width) (+ height menu-bar-height))
    (.setSize frame (Dimension. (+ width border-width) (+ height menu-bar-height)))
    #_(doseq [p (mandelbrot-points width height)] (mplot p))
    #_(dorun (map mplot (mandelbrot-points width height)))
    (dorun (pmap mplot ))))

