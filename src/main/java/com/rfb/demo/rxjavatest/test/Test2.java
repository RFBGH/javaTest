package com.rfb.demo.rxjavatest.test;

import com.google.gson.Gson;
import com.rfb.demo.rxjavatest.DelayUtil;
import com.rfb.demo.rxjavatest.test.bean.TestGsonList;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * Created by Administrator on 2018/5/18 0018.
 */
public class Test2{

    public static void testGSonListConver(){

        final TestGsonList testGsonList = new TestGsonList();

        new Thread(){

            @Override
            public void run() {
                for(int i = 0; i < 1000; i++){


                    Gson gson = new Gson();

                    TestGsonList clone = new TestGsonList();
                    clone.getData().addAll(testGsonList.getData());

                    System.out.println(gson.toJson(clone));

                    DelayUtil.delay(1);
                }
            }
        }.start();

        new Thread(){

            @Override
            public void run() {

                for(int i = 0; i < 10000; i++){

                    DelayUtil.delay(1);
                    testGsonList.getData().add("ext "+i);
                }
            }
        }.start();

    }

    Class<?> getClass(String name){
        return Object.class;
    }

    public interface ITest<T extends Object>{

    }

    public static void testUrlEncoding(){

        String path = "E://sdfa/asfaf/sfd/a.jpg";
        String encode = URLEncoder.encode(path);
        System.out.println(encode);
        System.out.println(URLDecoder.decode(encode));


    }

}
