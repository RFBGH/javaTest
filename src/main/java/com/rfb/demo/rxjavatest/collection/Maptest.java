package com.rfb.demo.rxjavatest.collection;

import java.util.*;

/**
 * Created by Administrator on 2018/3/22 0022.
 */
public class Maptest {

    public static void test(){

        Map tree = new TreeMap();
        Map linked = new LinkedHashMap();
        Map hash = new HashMap();

        System.out.println("tree :"+buildMap(tree));
        System.out.println("link :"+buildMap(linked));
        System.out.println("hash :"+buildMap(hash));

    }

    private static Map buildMap(Map map){
        map.put("0", "a");
        map.put("e", "b");
        map.put("4", "s");
        map.put("3", "c");
        return map;
    }

}
