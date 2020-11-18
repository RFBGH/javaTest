package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class GuessGame {

    int guess(int num){
        if(num > 6){
            return 1;
        }

        if(num < 6){
            return -1;
        }
        return 0;
    }

    public int guessNumber(int n) {
        long left = 1;
        long right = n;
        while (true){
            long mid = (left + right) / 2;
            if(guess((int)mid) == 0){
                return (int)mid;
            }

            if(guess((int)mid) == 1){
                left = mid+1;
            }else{
                right = mid;
            }
        }
    }

    public void test(){
        System.out.println(guessNumber(10));
    }
}
