package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class maxNumber {
    private boolean bigger(int[] num1, int from1, int[] num2, int from2){
        int i = from1;
        int j = from2;
        while (true){
            if(num1[i] > num2[j]){
                return true;
            }

            if(num1[i] < num2[j]){
                return false;
            }

            i++;
            j++;
            if(i == num1.length && j == num2.length){
                return false;
            }
            if(i == num1.length){
                i = from1;
            }
            if(j == num2.length){
                j = from2;
            }
        }
    }

    private int[] maxNumber(int[] num, int k){

        if(k == 0){
            return new int[]{};
        }

        if(k > num.length){
            return new int[]{};
        }

        int[] result = new int[k];
        int resultCount = 0;
        int start = 0;
        int rest = k;
        while (true){
            int max = Integer.MIN_VALUE;
            int firstMaxIndex = 0;
            for(int i = start, size = num.length-rest+1; i < size; i++){
                if(max < num[i]){
                    max = num[i];
                    firstMaxIndex = i;
                }
            }

            result[resultCount++] = max;
            rest = k - resultCount;
            start = firstMaxIndex+1;
            if(rest == 0){
                break;
            }
        }

        return result;
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int[] ans = new int[k];
        int[] temp = new int[k];

        for(int i = 0; i <= k; i++){

            int[] maxNum1 = maxNumber(nums1, i);
            int[] maxNum2 = maxNumber(nums2, k-i);
            if(maxNum1.length + maxNum2.length < k){
                continue;
            }

            int count = 0;
            int i1 = 0;
            int i2 = 0;

            while (i1 < maxNum1.length && i2 < maxNum2.length){
                if(bigger(maxNum1, i1, maxNum2, i2)){
                    temp[count++] = maxNum1[i1++];
                }else{
                    temp[count++] = maxNum2[i2++];
                }
            }

            while (i1 < maxNum1.length){
                temp[count++] = maxNum1[i1++];
            }

            while (i2 < maxNum2.length){
                temp[count++] = maxNum2[i2++];
            }

            if(bigger(temp, 0, ans, 0)){
                for(int j = 0; j < k; j++){
                    ans[j] = temp[j];
                }
            }
        }

        return ans;
    }
    public void test(){
        maxNumber(new int[]{2,5,6,4,4,0}, new int[]{7,3,8,0,6,5,7,6,2}, 15);
    }

}
