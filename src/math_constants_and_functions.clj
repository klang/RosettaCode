;; http://rosettacode.org/wiki/Math_constants_and_functions
(Math/E)
(Math/PI)
(Math/sqrt x)
(Math/log x)
(Math/exp x)
(Math/abs x)
(Math/floor x)
(Math/ceil x)
(Math/pow x y)

(ns user
  (:require [clojure.contrib.generic.math-functions :as generic]))

(generic/sqrt x)
(generic/log x)
(generic/exp x)
(generic/abs x)
(generic/floor x)
(generic/ceil x)
(generic/pow x y)

(ns user
  (:require [clojure.contrib.math :as math]))

(math/sqrt x)
(math/abs x)
(math/floor x)
(math/ceil x)
(math/expt x y) 

