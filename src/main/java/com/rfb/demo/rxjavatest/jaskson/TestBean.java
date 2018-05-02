package com.rfb.demo.rxjavatest.jaskson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

//@JsonIgnoreProperties(ignoreUnknown = true)
public class TestBean {

    @JsonProperty("test")
    @JsonDeserialize(using = BeanDeserialize.class)
    private long test;

    public long getTest() {
        return test;
    }
}
