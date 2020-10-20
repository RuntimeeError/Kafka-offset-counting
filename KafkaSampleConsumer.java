package myapps;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaSampleConsumer {
    public static void main(String[] args) throws InterruptedException {
        Logger logger = LoggerFactory.getLogger(KafkaSampleConsumer.class.getName());
        String bootstrapServers = "127.0.0.1:9092";
        String grp_id = "third_app";
        String topic = "my-first3";
        //Creating consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, grp_id);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1000);
        properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 20000);
        //creating consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        //Subscribing
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();

        consumer.subscribe(Arrays.asList(topic));
        //polling
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(20000));
            System.out.println(records.count());

            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
                 logger.info("Partition:" + record.partition()+",Offset:"+record.offset());
            }
        }
    }
}
