; In this task, the job is to create a file called "output.txt", and place in it the contents of the file "input.txt".

;; added to http://rosettacode.org/wiki/File/Input_and_Output#Clojure
(import '(java.io File 
		  FileReader BufferedReader
		  FileWriter BufferedWriter))

(.createNewFile (File. "input.txt"))
(def br (BufferedReader. (FileReader. "input.txt")))
(def bw (BufferedWriter. (FileWriter. "input.txt")))

(use 'clojure.contrib.duck-streams)
(spit  "input.txt"  "Hello, world!\n")
(spit "output.txt" (slurp "input.txt"))
(slurp "output.txt")
(copy "index.txt" "output.txt") 
