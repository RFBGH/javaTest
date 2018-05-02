package com.rfb.demo.rxjavatest.jaskson;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Administrator on 2017/10/26 0026.
 */

public class JacksonTest {


    public static void test(){

        String jsonContent = "{\"test\":\"12345\"}";

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            TestBean testBean = objectMapper.readValue(jsonContent, TestBean.class);
            System.out.println(testBean.getTest());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
