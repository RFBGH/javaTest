package com.rfb.demo.rxjavatest.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ServerTimeDeserializer extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
            JsonProcessingException {

        System.out.println("fuck xx");

        long time = TimeUtils.getTimeByString(jp.getText());
        return time;
    }
}
