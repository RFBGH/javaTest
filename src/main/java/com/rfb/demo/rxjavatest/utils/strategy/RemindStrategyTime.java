package com.rfb.demo.rxjavatest.utils.strategy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rfb.demo.rxjavatest.utils.ServerTimeDeserializer;
import com.rfb.demo.rxjavatest.utils.ServerTimeSerializer;

@JsonIgnoreProperties
public class RemindStrategyTime extends BaseRemindStrategy {
    @JsonProperty("cron_expression")
    private String mCronExpression = "";

    @JsonProperty("is_cyclic")
    private int  mIsCyclic =  0;

    @JsonProperty("start_time")
    @JsonSerialize(using = ServerTimeSerializer.class)
    @JsonDeserialize(using = ServerTimeDeserializer.class)
    private long mStartTime = 0;

    @JsonProperty("end_time")
    @JsonSerialize(using = ServerTimeSerializer.class)
    @JsonDeserialize(using = ServerTimeDeserializer.class)
    private long mEndTime = 0;

    public RemindStrategyTime(){
    }

    @JsonCreator
    public RemindStrategyTime(@JsonProperty("cron_expression") String pCron,
                              @JsonProperty("is_cyclic") int pIsCyclic,
                              @JsonProperty("start_time") long pStartTime,
                              @JsonProperty("end_time") long pEndTime){
        mCronExpression = pCron;
        mIsCyclic = pIsCyclic;
        mStartTime = pStartTime;
        mEndTime = pEndTime;
    }

    @JsonIgnore
    public String getCronExpression() {
        return mCronExpression;
    }
    @JsonIgnore
    public int getIsCyclic() {
        return mIsCyclic;
    }
    @JsonIgnore
    public void setCronExpression(String pCronExpression) {
        mCronExpression = pCronExpression;
    }
    @JsonIgnore
    public void setIsCyclic(int pIsCyclic) {
        mIsCyclic = pIsCyclic;
    }

    @JsonIgnore
    public void setStartTime(long pStartTime) {
        mStartTime = pStartTime;
    }
    @JsonIgnore
    public void setEndTime(long pEndTime) {
        mEndTime = pEndTime;
    }

    @JsonIgnore
    public long getStartTime() {
        return mStartTime;
    }
    @JsonIgnore
    public long getEndTime() {
        return mEndTime;
    }

}
