; In this task, the job is to create a new empty file called "output.txt" of size 0 bytes and an empty directory called "docs". This should be done twice: once "here", i.e. in the current working directory and once in the filesystem root.

;; added to http://rosettacode.org/wiki/File/Creation
(import '(java.io File))
(.createNewFile (File. "output.txt"))
(.mkdir (File. "docs"))

(.createNewFile (new File (str (File/separator) "output.txt")))
(.mkdir (new File (str (File/separator) "docs")))

(use 'clojure.contrib.duck-streams)
(spit  "input.txt"  "")
