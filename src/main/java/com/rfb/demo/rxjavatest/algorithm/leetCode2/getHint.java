package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.HashSet;
import java.util.Set;

public class getHint {

    public String getHint(String secret, String guess) {

        int n = secret.length();
        int[] same = new int[256];
        int sameCount = 0;
        int[] count = new int[256];
        for(int i = 0; i < n; i++){
            if(secret.charAt(i) == guess.charAt(i)){
                same[secret.charAt(i)]++;
                sameCount++;
            }else{
                count[secret.charAt(i)]++;
            }
        }

        int contain = 0;
        for(int i = 0; i < n; i++){
            if(same[guess.charAt(i)] != 0){
                same[guess.charAt(i)]--;
                continue;
            }

            if(count[guess.charAt(i)] == 0){
                continue;
            }

            count[guess.charAt(i)]--;
            contain++;
        }

        return sameCount+"A"+contain+"B";
    }

}
