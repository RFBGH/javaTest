package com.rfb.demo.rxjavatest.utils.strategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RemindStrategyTime.class, name = "TIME"),
        @JsonSubTypes.Type(value = RemindStrategyLocation.class, name = "LOCATION")
})
@JsonIgnoreProperties
public abstract class BaseRemindStrategy implements Serializable {
    public BaseRemindStrategy() {
    }
}
