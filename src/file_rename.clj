; In this task, the job is to rename the file called "input.txt" into "output.txt" and a directory called "docs" into "mydocs". This should be done twice: once "here", i.e. in the current working directory and once in the filesystem root

;; added to http://rosettacode.org/wiki/File/Rename#Clojure
(import '(java.io File))

(.renameTo (File. "input.txt") (File. "output.txt"))
(.renameTo (File. "docs") (File. "mydocs"))

(.renameTo 
 (new File (str (File/separator) "input.txt"))
 (new File (str (File/separator) "output.txt")))

(.renameTo 
 (new File (str (File/separator) "docs"))
 (new File (str (File/separator) "mydocs")))

