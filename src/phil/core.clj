(ns phil.core
  (:require
   [clojure.edn :as edn]
   [clojure.tools.cli :refer [parse-opts]]
   [taoensso.timbre :as timbre]
   [phil.listens :refer [start-listening]])
  (:gen-class))

(defn read-config [config-filename]
  (timbre/info "using config file:" config-filename)
  (edn/read-string (slurp config-filename)))

(def cli-opts
  [["-c" "--config config_phil.edn" "path to configuration file in edn format"
    :default "config_phil.edn"]])

(defn -main
  [& args]
  (let [{:keys [options]} (parse-opts args cli-opts)
        cwd (System/getProperty "user.dir")
        ;; in linux I just used ./default and it always just worked - the bare filename isn't slurping, despite cwd showing the right thing - I think it might be a drive-letter thing. For now, we'll prepend cwd.
        config-path (str cwd java.io.File/separator (:config options))
        config (read-config config-path)]
    (timbre/info "Hi, I'm PHIL!")
    (timbre/info "my current working directory is (maybe):" cwd)
    (timbre/info "I slurped this config:" config)
    (start-listening (:listens config))))
  
