(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [duct/core "0.7.0"]
                 [duct/handler.sql "0.4.0"]
                 [duct/module.ataraxy "0.3.0"]
                 [duct/module.logging "0.4.0"]
                 [duct/database.sql.hikaricp "0.4.0"]
                 [ragtime "0.8.0"]
                 [duct/module.web "0.7.0"]
                 [buddy/buddy-hashers "1.3.0"]
                 [org.postgresql/postgresql "42.2.5"]]
  :plugins [[duct/lein-duct "0.12.1"]]
  :main ^:skip-aot todo.main
  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]
  :aliases {"migrate" ["run" "-m" "user/migrate"]
            "rollback" ["run" "-m" "user/rollback"]}
  :middleware     [lein-duct.plugin/middleware]
  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user
                         :host "0.0.0.0"
                         :port "39998"}}
   :uberjar {:aot :all}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.3.1"]
                                   [eftest "0.5.7"]
                                   [com.gearswithingears/shrubbery "0.4.1"]
                                   [kerodon "0.9.0"]]}})
