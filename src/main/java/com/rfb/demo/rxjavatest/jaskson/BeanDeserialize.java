package com.rfb.demo.rxjavatest.jaskson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by Administrator on 2017/10/26 0026.
 */
public class BeanDeserialize extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Long l = 0L;

        l = Long.parseLong(jsonParser.getText());

        l += 1;

        return l;
    }
}
