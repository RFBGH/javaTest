package com.rfb.demo.rxjavatest.utils.strategy;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

@JsonAutoDetect
@JsonIgnoreProperties(ignoreUnknown = true)
public class MockStrategy {
    @JsonProperty("strategy")
    @JsonDeserialize(contentAs = BaseRemindStrategy.class)
    protected List<BaseRemindStrategy> mStrategies = new ArrayList<BaseRemindStrategy>();

    @JsonIgnore
    public List<BaseRemindStrategy> getStrategies(){
        return mStrategies;
    }

    public void setStrategies(List<BaseRemindStrategy> pStrategies){
        mStrategies = pStrategies;
    }
}
