(ns leiningen.publish
  (:require [leiningen.run :as run]
            [leiningen.rsync :as rsync]))

(defn publish [project & args]
  (run/run project)
  (rsync/rsync project "resources/public/" "master"))