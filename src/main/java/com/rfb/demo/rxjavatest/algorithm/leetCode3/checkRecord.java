package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class checkRecord {

    public boolean checkRecord(String s) {

        int A = 0;
        int L = 0;

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'A'){
                A++;
                if(A > 1){
                    return false;
                }
            }else if(c == 'L'){

                if(i > 0){
                    char lastC = s.charAt(i-1);
                    if(lastC == 'L'){
                        L++;
                    }else{
                        L = 1;
                    }
                }else{
                    L = 1;
                }

                if(L > 2){
                    return false;
                }
            }
        }

        return true;
    }

    public void test(){
        System.out.println(checkRecord("LLLALL"));
    }
}
