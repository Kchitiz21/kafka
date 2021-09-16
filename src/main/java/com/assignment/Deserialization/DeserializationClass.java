package com.assignment.Deserialization;

import com.assignment.StudentInfo.StudentInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class DeserializationClass implements Deserializer<StudentInformation.User> {
    @Override
    public StudentInformation.User deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        StudentInformation.User user = null;
        try {
            user = objectMapper.readValue(bytes, StudentInformation.User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
