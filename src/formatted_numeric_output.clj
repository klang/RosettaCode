;;common lisp
(use 'clojure.contrib.pprint)
(cl-format true "~9,3,,,'0F" 7.125)

;;java
(printf "%09.3f" 7.125)

;;as a value
(format "%09.3f" 7.125)