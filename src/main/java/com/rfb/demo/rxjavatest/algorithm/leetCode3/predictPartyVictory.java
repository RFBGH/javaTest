package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class predictPartyVictory {


    public String predictPartyVictory(String senate) {

        int n = senate.length();
        boolean[] losts = new boolean[n];
        for(int i = 0; i < n; i = (i+1) % n){
            char c = senate.charAt(i);
            if(losts[i]){
                continue;
            }

            if(c == 'R'){
                int j;
                for(j = (i+1)%n; j != i; j = (j+1) % n){
                    if(losts[j]){
                        continue;
                    }

                    char t = senate.charAt(j);
                    if(t == 'R'){
                        continue;
                    }

                    losts[j] = true;
                    break;
                }

                if(j == i){
                    return "Radiant";
                }
            }else{
                int j;
                for(j = (i+1)%n; j != i; j = (j+1) % n){
                    if(losts[j]){
                        continue;
                    }

                    char t = senate.charAt(j);
                    if(t == 'D'){
                        continue;
                    }

                    losts[j] = true;
                    break;
                }

                if(j == i){
                    return "Dire";
                }
            }
        }

        return "";
    }

    public void test(){

        System.out.println(predictPartyVictory("RDD"));

    }
}
