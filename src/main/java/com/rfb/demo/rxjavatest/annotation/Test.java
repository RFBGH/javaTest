package com.rfb.demo.rxjavatest.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by RFB on 2018/5/2.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    int id() default 1;

    String desc() default "";

}
