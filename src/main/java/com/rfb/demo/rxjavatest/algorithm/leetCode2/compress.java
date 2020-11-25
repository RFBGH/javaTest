package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class compress {

    public int compress(char[] chars) {

        int count = 1;
        int n = chars.length;
        List<Character> result = new ArrayList<>();

        for(int i = 1; i < n; i++){
            if (chars[i] == chars[i-1]) {
                count++;
            }else{
                result.add(chars[i-1]);
                if(count > 1){
                    List<Character> sum = new ArrayList<>();
                    while (count != 0){
                        sum.add((char)('0'+(count % 10)));
                        count /= 10;
                    }
                    Collections.reverse(sum);
                    result.addAll(sum);
                }
                count = 1;
            }
        }

        result.add(chars[n-1]);
        if(count > 1){
            List<Character> sum = new ArrayList<>();
            while (count != 0){
                sum.add((char)('0'+(count % 10)));
                count /= 10;
            }
            Collections.reverse(sum);
            result.addAll(sum);
        }

        for(int i = 0; i < result.size(); i++){
            chars[i] = result.get(i);
        }

        return result.size();
    }
}
