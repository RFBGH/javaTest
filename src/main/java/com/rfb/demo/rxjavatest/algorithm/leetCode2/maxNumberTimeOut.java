package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class maxNumberTimeOut {


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

        maxNumber(new int[]{9,8,7,6,5,4,3,2,1}, 4);
//        maxNumber(new int[]{3,3,3,2,3,7,3,8,6,0,5,0,7,8,9,2,9,6,6,9,9,7,9,7,6,1,7,2,7,5,5,1}, new int[]{5,6,4,9,6,9,2,2,7,5,4,3,0,0,1,7,1,8,1,5,2,5,7,0,4,3,8,7,3,8,5,3,8,3,4,0,2,3,8,2,7,1,2,3,8,7,6,7,1,1,3,9,0,5,2,8,2,8,7,5,0,8,0,7,2,8,5,6,5,9,5,1,5,2,6,2,4,9,9,7,6,5,7,9,2,8,8,3,5,9,5,1,8,8,4,6,6,3,8,4,6,6,1,3,4,1,6,7,0,8,0,3,3,1,8,2,2,4,5,7,3,7,7,4,3,7,3,0,7,3,0,9,7,6,0,3,0,3,1,5,1,4,5,2,7,6,2,4,2,9,5,5,9,8,4,2,3,6,1,9}, 160);
    }

}
