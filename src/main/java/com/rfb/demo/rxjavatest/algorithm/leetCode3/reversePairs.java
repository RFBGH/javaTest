package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class reversePairs {

    private int dfs(int[] nums, int left, int right){

        if(left == right){
            return 0;
        }

        int mid = (left + right) / 2;
        int sumLeft = dfs(nums, left, mid);
        int sumRight = dfs(nums, mid+1, right);

        int sumMid = 0;

        int[] temp = new int[right-left+1];
        int offset = 0;
        int i = left;
        int j = mid+1;

        for(; i <= mid; i++){
            for(; j <= right; j++){
                long l1 = nums[i];
                long l2 = 2*(long)nums[j];
                if(l1 > l2){
                    sumMid += (mid-i+1);
                }else{
                    break;
                }
            }
        }

        i = left;
        j = mid+1;
        while (i <= mid && j <= right){
            if(nums[i] < nums[j]){
                temp[offset++] = nums[i++];
            }else{
                temp[offset++] = nums[j++];
            }
        }

        while (i <= mid){
            temp[offset++] = nums[i++];
        }

        while (j <= right){
            temp[offset++] = nums[j++];
        }

        for(i = left; i <= right; i++){
            nums[i] = temp[i-left];
        }

        return sumLeft + sumRight + sumMid;
    }

    public int reversePairs(int[] nums) {
        int result = dfs(nums, 0, nums.length-1);
        return result;
    }

    public void test(){
        System.out.println(reversePairs(new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647}));
    }
}

