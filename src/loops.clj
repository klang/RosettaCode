(ns rosettacode)


(doseq [a (take 10 (iterate inc 1))] (println a))
(doseq [a (take 10 (iterate inc 1))] (println a))

(doseq [a (take 10 (take-while #(not (zero? (mod % 6))) (iterate inc 1)))] 
  (println a))

(take-while #(not (zero? (mod % 6))) (iterate inc 1))
(doseq [a (take-while #(not (zero? (mod % 6))) (iterate inc 1))] 
  (println a))


;; 10x10 matrix of random numbers
(doseq [a (take 10 (iterate inc 1))]
  (doseq [b (take 10 (repeatedly #(rand-int 10)))] (print b))
  (println)
  )

;; perl 
;; my $a = [ map [ map { int(rand(20)) + 1 } 1 .. 10 ], 1 .. 10];
(def matrix (for [x (range 10)] (take 10 (repeatedly #(rand-int 10)))))


(doseq [y matrix]
  (doseq [x y] (print x)) 
  (println))


(dotimes [n 10] (print n))

(def lo (loop [value 0]
  (if (not (zero? (mod value 6)))
    (do (println value)  
	(recur (assoc value (inc value))))) ))


(loop [[value b & more] (iterate inc 1)]
  (println value)
  (when-not (zero? (mod value 6))
    (do (println b) 
	(recur more))
    ))

(loop [[a b & more] 
       (repeatedly #(rand-int 20))]
  (println a)
  (when-not (= 10 a) 
    (println b) 
    (recur more)))

(loop [[b & more] 
       (repeatedly #(rand-int 20))]
  (when-not (= 10 b) 
    (println b) 
    (recur more)))

(loop [[b & more] 
       (repeatedly #(rand-int 20))]
  (when-not (zero? (mod b 6)) 
    (println b) 
    (recur more)))

;; http://rosettacode.org/wiki/Iterating_over_an_Associative_Array#Clojure
(doseq [[k v] {:a 1, :b 2, :c 3}]
  (println k "=" v))
 
(doseq [k  (keys {:a 1, :b 2, :c 3})]
  (println k))
 
(doseq [v  (vals {:a 1, :b 2, :c 3})]
  (println v))
