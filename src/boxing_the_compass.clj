(ns boxing-the-compass
  (:use clojure.test)
  (:use [clojure.contrib.string :only (replace-str)]))

(def headings
     (for [i (range 0 (inc 32))]
       (let [heading (* i 11.25)
	     case (mod i 3)]
	 (cond (= case 1) (+ heading 5.62)
	       (= case 2) (- heading 5.62)
	       :else heading))))

(defn capitalize [s]
  (str (Character/toUpperCase (first s)) (apply str (rest s))))

(defn angle2compass
  [angle]
  (let [dirs ["N" "NbE" "N-NE" "NEbN" "NE" "NEbE" "E-NE" "EbN"
	      "E" "EbS" "E-SE" "SEbE" "SE" "SEbS" "S-SE" "SbE"
	      "S" "SbW" "S-SW" "SWbS" "SW" "SWbW" "W-SW" "WbS"
	      "W" "WbN" "W-NW" "NWbW" "NW" "NWbN" "N-NW" "NbW"]
	unpack {\N "north" \E "east" \W "west" \S "south" \b " by " \- "-"}
	sep  (/ 360 (count dirs))
	dir  (int (/ (mod (+ angle (/ sep 2)) 360) sep))]
    (capitalize (apply str (map #(unpack %)  (seq (dirs dir)))))))

(comment
  (print
   (apply str (map #(format "%2s %-18s %7.2f\n"
			    (+ (mod %1 32) 1) (angle2compass %2) %2)
		   (iterate inc 0) headings)))
)
