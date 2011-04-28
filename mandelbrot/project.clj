(defproject mandelbrot "1.0.0-SNAPSHOT"
  :description "simple example of drawing the mandelbrot set"
  :source-path "src"
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]]
  :dev-dependencies [[swank-clojure "1.2.1"]]
  :jvm-opts ["-Xmx64M"])
