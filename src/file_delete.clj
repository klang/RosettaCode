; In this task, the job is to delete a file called "input.txt" and delete a directory called "docs". This should be done twice: once "here", i.e. in the current working directory and once in the filesystem root.

;; added to http://rosettacode.org/wiki/File/Delete
(import '(java.io File))
(.delete (File. "output.txt"))
(.delete (File. "docs"))

(.delete (new File (str (File/separator) "output.txt")))
(.delete (new File (str (File/separator) "docs")))

