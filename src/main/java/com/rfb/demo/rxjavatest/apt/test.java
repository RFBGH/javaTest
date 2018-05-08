package com.rfb.demo.rxjavatest.apt;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Administrator on 2018/5/2 0002.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface test {
    Class value();
}
