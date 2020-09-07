package com.rfb.demo.rxjavatest.exception;

import java.lang.reflect.Method;

public class ExceptionTest {



    public static class ExtraErrorInfo{

        private String mCode;

        public String getCode() {
            return mCode;
        }

        public void setCode(String mCode) {
            this.mCode = mCode;
        }
    }

    public static class MyException extends Exception{

        private ExtraErrorInfo mExtraErrorInfo;

        public MyException(String message) {
            super(message);

            mExtraErrorInfo = new ExtraErrorInfo();
            mExtraErrorInfo.setCode("fuck");
        }

        public ExtraErrorInfo getExtraErrorInfo() {
            return mExtraErrorInfo;
        }
    }

    public static void test(){

        Throwable e = new MyException("myException");
        System.out.println(e.getClass().getName());

        try{
            String errCode = "";
            Method getExtraErrorInfoMethod = e.getClass().getMethod("getExtraErrorInfo");
            Object extraErrorInfo = getExtraErrorInfoMethod.invoke(e);
            if(extraErrorInfo != null){
                Method getCodeMethod = extraErrorInfo.getClass().getMethod("getCode");
                errCode = (String)getCodeMethod.invoke(extraErrorInfo);
            }

            System.out.println(errCode);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }
}
