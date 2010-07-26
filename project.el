(defwrk rosetta-code-current "problems currently being worked on"
  "~/projects/RosettaCode/src"
  )

(defun rosetta-code nil
  "Start Rosetta Code"
  (interactive)
  (setq default-directory "~/projects/RosettaCode/")
  (load "~/projects/gists/gist-337280/clojure-font-lock-setup.el")
  (rosetta-code-current)
  (add-hook 'slime-connected-hook 'slime-redirect-inferior-output)
;  (swank-clojure-project default-directory)
  )
