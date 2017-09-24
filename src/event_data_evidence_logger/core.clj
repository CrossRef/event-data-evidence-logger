(ns event-data-evidence-logger.core
  "Monitor a Kafka topic and ingest Events from it.
  Use the same interface as the server's POST interface."
  (:require 
    [config.core :refer [env]]
    [clojure.tools.logging :as log])
  (:import [org.apache.kafka.clients.consumer KafkaConsumer Consumer ConsumerRecords])
  (:gen-class))

(defn run
 [] 
 (let [consumer (KafkaConsumer.
                  {"bootstrap.servers" (:global-kafka-bootstrap-servers env)
                   "group.id" "evidence-logger"
                   "key.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
                   "value.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"})
       topic-name (:global-status-topic env)]

   (log/info "Subscribing to" topic-name)
   (.subscribe consumer (list topic-name))
   
   (loop []
     (let [^ConsumerRecords records (.poll consumer (int 10000))]
       (doseq [record records]
        (log/info (.value record))))
      (recur))))

(defn -main
  [& args]
  (run))
