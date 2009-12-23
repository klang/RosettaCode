; This task will attempt to get and set the modification time of a file.

;; added to http://rosettacode.org/wiki/File/Modification_Time#Clojure
(import '(java.io File)
	'(java.util Date))

(Date. (.lastModified (File. "output.txt")))
(Date. (.lastModified (File. "docs")))

(.setLastModified (File. "output.txt") 
		  (.lastModified (File. "docs")))

