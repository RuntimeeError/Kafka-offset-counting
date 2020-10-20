package myapps;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaSampleProducer {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> first_producer = new KafkaProducer<String, String>(props);

         for (int i=1 ; i<=20000; i++){
             System.out.println("sending messages " + i);
             ProducerRecord<String, String> record=new ProducerRecord<String, String>("my-first3", "Hye Kafka "+i);
             first_producer.send(record);
         }

        first_producer.flush();
        first_producer.close();
    }
}
