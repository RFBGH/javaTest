package com.rfb.demo.rxjavatest.annotation;

import java.lang.reflect.Field;

/**
 * Created by RFB on 2018/5/2.
 */
public class TestAnnotationUtil {


    public static void findAno(TestBean testBean) throws IllegalAccessException, NoSuchFieldException {

        Field[] fields = testBean.getClass().getDeclaredFields();
        for(Field field:fields){
            Test test = field.getDeclaredAnnotation(Test.class);

            if(test == null){
                continue;
            }

            field.setAccessible(true);
            field.set(testBean, test.desc());
        }

        Field testField = testBean.getClass().getField("test");
        System.out.println(testField.get(testBean.getClass()));
    }

    public static void test(){

        TestBean testBean = new TestBean();


        try {
            findAno(testBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(testBean.toString());


    }

}
