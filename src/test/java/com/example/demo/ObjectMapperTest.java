package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by LXX on 2018/12/1.
 */
public class ObjectMapperTest {

    public static void main(String[] args) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\n" +
                "\t\"userName\":\"user1\",\n" +
                "\t\"password\":\"123456\"\n" +
                "}";
        System.out.println((User)ObjectMapperUtil.getObjectMapper().readValue(json, new TypeReference<User>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        }));
    }
}
