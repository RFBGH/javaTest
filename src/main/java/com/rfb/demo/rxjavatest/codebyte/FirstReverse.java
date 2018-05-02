package com.rfb.demo.rxjavatest.codebyte;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
public class FirstReverse {

    public static String FirstReverse(String str) {

        // code goes here
    /* Note: In Java the return type of a function and the
       parameter types being passed are defined, so this return
       call must match the return type of the function.
       You are free to modify the return type. */

        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();

    }

}
