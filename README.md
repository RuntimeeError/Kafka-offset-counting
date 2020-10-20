# Kafka-polling-messages-batch
It counts the messages that pass through zookeeper and can be used in application where real time message counting is desired with polling and batch processing
This repo contains two java files namely kafka producer and consumer.
The goal is the produce large number of messages and consume it in batches 
To achieve this polling feature of zookeeper is used.
Start kafka server and zookeeper on your local or server environment and run the producer and consumer independently.
Make sure you change topic name , polling time as required.
