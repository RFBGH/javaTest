package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class maxNumber {

    private boolean bigger(int[] nums1, int[] nums2, int n){
        for(int i = 0; i < n; i++){
            if(nums1[i] > nums2[i]){
                return true;
            }else if(nums1[i] < nums2[i]){
                return false;
            }
        }
        return false;
    }

    private int[] ans;
    private void dfs(int[] nums1, int[] nums2, int k, int i, int j, int[] result, int len){

        if(k == len){
            if(bigger(result, ans, k)){
                for(int t = 0; t < k; t++){
                    ans[t] = result[t];
                }
            }
            return;
        }

        for(int t = i; t < nums1.length; t++){
            result[len] = nums1[t];
            dfs(nums1, nums2, k, t+1, j, result, len+1);
        }

        for(int t = j; t < nums2.length; t++){
            result[len] = nums2[t];
            dfs(nums1, nums2, k, i, t+1, result, len+1);
        }
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        ans = new int[k];
        dfs(nums1, nums2, k, 0, 0, new int[k], 0);
        return ans;
    }

    public void test(){
        maxNumber(new int[]{2,5,6,4,4,0}, new int[]{7,3,8,0,6,5,7,6,2}, 15);
    }

}
