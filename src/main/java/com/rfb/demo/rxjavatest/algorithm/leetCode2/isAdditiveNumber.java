package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class isAdditiveNumber {

    private int findNextStringStartWithNum(String s, int index, List<Integer> num){

        int n = s.length();
        if(num.size() > n-index){
            return -1;
        }

        for(int i = num.size()-1; i >=0; i--){
            if(num.get(i) != s.charAt(index++)-'0'){
                return -1;
            }
        }
        return index;
    }

    private List<Integer> add(List<Integer> num1, List<Integer> num2){
        int i = 0;
        int n = num1.size();
        int j = 0;
        int m = num2.size();
        int carry = 0;
        List<Integer> sum = new ArrayList<>();
        while (i < n && j < m){
            int num3 = num1.get(i) + num2.get(j) + carry;
            sum.add(num3 % 10);
            carry = num3 / 10;
            i++;
            j++;
        }

        while (i < n){
            int num3 = num1.get(i) + carry;
            sum.add(num3 % 10);
            carry = num3 / 10;
            i++;
        }

        while (j < m){
            int num3 = num2.get(j) + carry;
            sum.add(num3 % 10);
            carry = num3 / 10;
            j++;
        }

        if(carry != 0){
            sum.add(carry);
        }

        return sum;
    }

    public boolean isAdditiveNumber(String num) {

        List<Integer> startNum1 = new ArrayList<>();
        List<Integer> startNum2 = new ArrayList<>();
        List<Integer> sum;
        int index = 0;
        int n = num.length();
        for(int i = 0; i < n; i++){
            startNum1.add(num.charAt(i) - '0');
            startNum2.clear();

            for(int j = i+1; j < n; j++){
                startNum2.add(num.charAt(j) - '0');
                index = j+1;

                List<Integer> num1 = new ArrayList<>(startNum1);
                List<Integer> num2 = new ArrayList<>(startNum2);
                Collections.reverse(num1);
                Collections.reverse(num2);
                while (true){
                    sum = add(num1, num2);
                    index = findNextStringStartWithNum(num, index, sum);
                    if(index == -1){
                        break;
                    }

                    if(index == n){
                        return true;
                    }

                    num1 = num2;
                    num2 = sum;
                }

                if(num.charAt(i+1) == '0'){
                    break;
                }
            }

            if(num.charAt(0) == '0'){
                break;
            }
        }

        return false;
    }

    public void test(){
        System.out.println(isAdditiveNumber("112358"));
        System.out.println(isAdditiveNumber("199100199"));
        System.out.println(isAdditiveNumber("1023"));
        System.out.println(isAdditiveNumber("101"));
        System.out.println(isAdditiveNumber("0235813"));
        System.out.println(isAdditiveNumber("121474836472147483648"));
    }
}
