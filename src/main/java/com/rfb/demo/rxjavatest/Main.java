package com.rfb.demo.rxjavatest;

import com.rfb.demo.rxjavatest.bean.A;
import com.rfb.demo.rxjavatest.directory_filename_compare.DirectorySubFileNameCompareUtil;
import com.rfb.demo.rxjavatest.reflect.FinalStaticChangeTest;
import com.rfb.demo.rxjavatest.test.Test1;
import com.rfb.demo.rxjavatest.test.Test2;
import com.rfb.demo.rxjavatest.test.Test3;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class Main {

    public static class TestBean{

        private String test;

        public TestBean(String test) {
            this.test = test;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestBean testBean = (TestBean) o;

            if (test != null ? !test.equals(testBean.test) : testBean.test != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return test != null ? test.hashCode() : 0;
        }
    }



    public static void main(String[] args) {

        Test3.test();
//        String s = null;
//        if(s instanceof String){
//            System.out.println("yes");
//        }else{
//            System.out.println("no");
//        }

//        List<SoftReference<TestBean>> testBeans = new ArrayList<SoftReference<TestBean>>();
//
//        for(int i = 0; i < 10; i++){
//
//            SoftReference<TestBean> softReference = new SoftReference<TestBean>(new TestBean(i+""));
//            testBeans.add(softReference);
//        }
//
//        TestBean testBean = new TestBean(4+"");
//        testBeans.remove(testBean);
//
//        for(SoftReference<TestBean> softReference:testBeans){
//
//            if(softReference.get() == null){
//                System.out.println("null");
//            }else{
//                System.out.println(softReference.get().test);
//            }
//
//        }
//        A  a = new A(100L);
//        Class clazz = a.getClass();


//        Integer integer = new Integer(5);
//
//        int i = (int)integer;
//
//        System.out.print(i);
//
////        String s = "xxxx";
////        System.out.println(s+" "+s.toString());
//        Test1.main1();
    }

}

