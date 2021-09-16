package com.assignment.Consumer;

import com.assignment.StudentInfo.StudentInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.assignment.Deserialization.DeserializationClass");
        properties.put("group.id", "user-group");

        KafkaConsumer<String, StudentInformation.User> kafkaConsumer = new KafkaConsumer<>(properties);
        List topics = new ArrayList();
        topics.add("user");
        kafkaConsumer.subscribe(topics);
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            while (true) {
                FileWriter fileWriter = new FileWriter("Student-data.txt", true);

                ConsumerRecords<String, StudentInformation.User> consumerRecords = kafkaConsumer.poll(Duration.ofMinutes(1));

                for (ConsumerRecord<String, StudentInformation.User> consumerRecord : consumerRecords) {
                    System.out.printf(
                            "Topic: %s, Partition: %d, Value: %s%n",
                            consumerRecord.topic(),
                            consumerRecord.partition(),
                            consumerRecord.value().toString());

                    fileWriter.write(consumerRecord.value().toString() + "\n");
                }
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }
}