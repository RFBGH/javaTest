package com.rfb.demo.rxjavatest.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class TestMatcher {

    public void testGroup(){

        String regEx = "\\#(\\d+)\\#";
        String s = "#161#2#22#*****";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(s);
        while (mat.find()){
            System.out.println(mat.groupCount());
            System.out.println(mat.group());
            System.out.println(mat.group(0));
            System.out.println(mat.group(1));
        }
    }

}

