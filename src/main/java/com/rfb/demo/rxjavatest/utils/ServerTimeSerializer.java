package com.rfb.demo.rxjavatest.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ServerTimeSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        System.out.println("fuck yy");

        jgen.writeString(TimeUtils.getTimeString(value));
    }
}
