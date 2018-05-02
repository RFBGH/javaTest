package com.rfb.demo.rxjavatest.codebyte;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
public class LongestWord {

    public static String LongestWord(String sen) {

        String ans = "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0, size = sen.length(); i < size; i++){

            char c = sen.charAt(i);
            if ((c >= 'a' && c <= 'z' )
                || (c >= 'A' && c <= 'Z')
                || (c >= '0' && c <= '9')){
                sb.append(c);
            }else{
                if(sb.length() > ans.length()){
                    ans = sb.toString();
                }
                sb = new StringBuilder();
            }
        }

        if(sb.length() > ans.length()){
            ans = sb.toString();
        }

        return ans;

    }
}
