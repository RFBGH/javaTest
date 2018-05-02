package com.rfb.demo.rxjavatest.utils.strategy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemindStrategyLocation extends BaseRemindStrategy {

    @JsonProperty("longitude")
    private String mLongitude = "";

    @JsonProperty("latitude")
    private String mLatitude = "";

    @JsonProperty("label")
    private String mLabel = "";

    @JsonProperty("scale")
    private String mScale = "";

    public RemindStrategyLocation(){
    }

    @JsonCreator
    public RemindStrategyLocation(@JsonProperty("longitude") String pLong,
                                  @JsonProperty("latitude") String pLat,
                                  @JsonProperty("label") String pLabel,
                                  @JsonProperty("scale") String pScale){
        mLongitude = pLong;
        mLatitude = pLat;
        mLabel = pLabel;
        mScale = pScale;
    }
    @JsonIgnore
    public String getLongitude() {
        return mLongitude;
    }
    @JsonIgnore
    public String getLatitude() {
        return mLatitude;
    }
    @JsonIgnore
    public String getLabel() {
        return mLabel;
    }

    @JsonIgnore
    public void setLongitude(String pLongitude) {
        mLongitude = pLongitude;
    }
    @JsonIgnore
    public void setLatitude(String pLatitude) {
        mLatitude = pLatitude;
    }
    @JsonIgnore
    public void setLabel(String pLabel) {
        mLabel = pLabel;
    }

    public String getScale() {
        return mScale;
    }

    public void setScale(String pScale) {
        mScale = pScale;
    }

}
