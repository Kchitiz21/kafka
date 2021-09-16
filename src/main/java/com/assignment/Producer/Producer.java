package com.assignment.Producer;

import com.assignment.StudentInfo.StudentInformation;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.assignment.Serialization.SerializationClass");

        KafkaProducer<String, StudentInformation.User> kafkaProducer = new KafkaProducer<>(properties);

        try {
            for (int counter = 1; counter <= 60; counter++) {
                StudentInformation.User user = new StudentInformation.User(counter, "Kchitiz Deep", (int) (20 + (Math.random() * 40)), "B.Tech");

                kafkaProducer.send(new ProducerRecord("user", String.valueOf(user.getId()), user));

                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}