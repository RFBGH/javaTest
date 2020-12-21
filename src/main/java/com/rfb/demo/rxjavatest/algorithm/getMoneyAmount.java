package com.rfb.demo.rxjavatest.algorithm;

public class getMoneyAmount {

    public int getMoneyAmount(int n) {

        int left = 1;
        int right = n;
        int sum = 0;

        while (true){

            if(left == right){
                break;
            }

            int mid = (left + right) / 2;
            sum += mid;
            left = mid+1;
        }

        return sum;
    }

    public void test(){
        System.out.println(getMoneyAmount(10));
    }

}
