package com.rfb.demo.rxjavatest.test.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Administrator on 2018/5/21 0021.
 */
public class TestGsonList {

    @SerializedName("data")
    private List<String> mData = new Vector<String>();

    public TestGsonList() {

    }

    public List<String> getData() {
        return mData;
    }
}
