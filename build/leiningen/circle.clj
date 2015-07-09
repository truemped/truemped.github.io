(ns leiningen.circle
  (:require [leiningen.run :as run]
            [leiningen.rsync :as rsync]))

(defn circle [project & args]
  (run/run project)
  (rsync/rsync project "resources/public/" "master"))
