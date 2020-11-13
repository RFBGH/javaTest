package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class countSmaller2 {

    private void dfs(int[] nums, int[]indexs, int left, int right, int[] count){

        if(left == right){
            return;
        }

        int mid = (left + right) / 2;
        dfs(nums, indexs, left, mid, count);
        dfs(nums, indexs, mid+1, right, count);

        int e = left;
        for(int t = mid+1; t <= right; t++){
            for(; e <= mid; e++){
                if(nums[t] <= nums[e]){
                    break;
                }
            }

            count[indexs[t]] += e - left;
        }

        int i = left;
        int j = mid+1;
        int[] temp = new int[right-left+1];
        int[] tempIndex = new int[right-left+1];
        int k = 0;

        while (i <= mid && j <= right){
            if(nums[i] < nums[j]){
                temp[k] = nums[i];
                tempIndex[k] = indexs[i];
                k++;
                i++;
            }else{
                temp[k] = nums[j];
                tempIndex[k] = indexs[j];
                k++;
                j++;
            }
        }

        while (i <= mid){
            temp[k] = nums[i];
            tempIndex[k] = indexs[i];
            k++;
            i++;
        }

        while (j <= right){
            temp[k] = nums[j];
            tempIndex[k] = indexs[j];
            k++;
            j++;
        }

        for(i = left; i <= right; i++){
            nums[i] = temp[i-left];
            indexs[i] = tempIndex[i-left];
        }
    }

    public List<Integer> countSmaller(int[] nums) {

        if(nums.length == 0){
            return new ArrayList<>();
        }

        int n = nums.length;
        int i = 0;
        int j = nums.length-1;
        while (true){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
            if(i >= j){
                break;
            }
        }

        int[] indexs = new int[n];
        for(i = 0; i < n; i++){
            indexs[i] = i;
        }

        int[] count = new int[n];
        dfs(nums, indexs, 0, n-1, count);

        List<Integer> result = new ArrayList<>();
        for(i = n-1; i >= 0; i--){
            result.add(count[i]);
        }

        return result;
    }

    public void test(){
        countSmaller(new int[]{1,1});
    }
}
