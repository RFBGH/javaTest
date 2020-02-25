package com.rfb.demo.rxjavatest.class_test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/11/27 0027.
 */
public class Test {

    private Map<Class<? extends ITest>, String> mClassITestMap = new HashMap<Class<? extends ITest>, String>();

    public void test(){

        mClassITestMap.put(Test_A.class, "TestA");
        mClassITestMap.put(Test_B.class, "TestB");

        ITest testB = new Test_B();

        String sB = mClassITestMap.get(testB.getClass());
        System.out.println(sB);

        ITest testA = new Test_A();

        String sA = mClassITestMap.get(testA.getClass());
        System.out.println(sA);
    }

}
