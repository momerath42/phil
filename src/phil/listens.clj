(ns phil.listens
  (:require
   [taoensso.timbre :as timbre]
   ;;[libpython-clj.require :refer [require-python]]
   [libpython-clj.python :as py]
   ))
(timbre/refer-timbre)

(defn start-listening [cfg]
  (info "listens/start-listening cfg:" cfg)
  (py/initialize! :python-executable (:python-executable cfg)
                  :library-path (:library-path cfg)
                  :windows-anaconda-activate-bat (:windows-anaconda-activate-bat cfg)))
