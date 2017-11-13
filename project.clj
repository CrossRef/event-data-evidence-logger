(defproject event-data-evidence-logger "0.1.1"
  :description "Event Bus Evidence Logger"
  :url "http://eventdata.crossref.org/"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [event-data-common "0.1.37"]
                 [crossref-util "0.1.10"]
                 [yogthos/config "0.8"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.apache.logging.log4j/log4j-core "2.6.2"]
                 [org.slf4j/slf4j-simple "1.7.21"]
                 [org.apache.kafka/kafka-clients "0.10.2.0"]]
  :main ^:skip-aot event-data-evidence-logger.core
  :jvm-opts ["-Duser.timezone=UTC" "-Xmx2G"]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :prod {:resource-paths ["config/prod"]}
             :dev  {:resource-paths ["config/dev"]}})
