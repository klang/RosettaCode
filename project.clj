(defproject RosettaCode "0.1"
  :description "Translation programming tasks from Rosetta Code. http://rosettacode.org"
  :url "http://github.com/klang/RosettaCode"
  ;;:jar-behavior :symlink
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 ]
  :dev-dependencies [[swank-clojure "1.2.1"]]
  ;:main dispatch
  :jvm-opts ["-Xmx512M"])

