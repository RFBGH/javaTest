package com.rfb.demo.rxjavatest.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/25 0025.
 */
public class StaticTest {

    public static List<String> list = new ArrayList<String>();

    static {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
    }

    public void test(){

        for(String s:list){
            System.out.print(s+" ");
        }
        System.out.println();
    }
}
