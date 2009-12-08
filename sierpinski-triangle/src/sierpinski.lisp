;; http://rosettacode.org/wiki/Sierpinski_triangle#Common_Lisp

(defun print-sierpinski (order)
  (loop with size = (expt 2 order)
        repeat size
        for v = (expt 2 (1- size)) then (logxor (ash v -1) (ash v 1))
        do (fresh-line)
           (loop for i below (integer-length v)
                 do (princ (if (logbitp i v) "*" " ")))))

;; Printing each row could also be done by printing the integer in base 2 and replacing zeroes with spaces: (princ (substitute #\Space #\0 (format nil "~%~2,vR" (1- (* 2 size)) v)))

;; Replacing the iteration with for v = 1 then (logxor v (ash v 1)) produces a "right" triangle instead of an "equilateral" one. 
