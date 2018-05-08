package com.rfb.demo.rxjavatest.annotation;

/**
 * Created by RFB on 2018/5/2.
 */
public class TestBean {

    public static String test = "xxxx";

    @Test(id = 1, desc = "11")
    private String mStr1;

    @Test(id = 2, desc = "22")
    private String mStr2;

    @Test(id = 3, desc = "33")
    private String mStr3;


    public TestBean(){

    }

    @Override
    public String toString() {

        return mStr1+" "+mStr2+" "+mStr3;
    }
}
