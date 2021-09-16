package com.assignment.Serialization;

import com.assignment.StudentInfo.StudentInformation;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Serializer;

public class SerializationClass implements Serializer<StudentInformation.User> {
    @Override
    public byte[] serialize(String s, StudentInformation.User user) {
        byte[] userToByte = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userToByte = objectMapper.writeValueAsString(user).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userToByte;
    }
}

