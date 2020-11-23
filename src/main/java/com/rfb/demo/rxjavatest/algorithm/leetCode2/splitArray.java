package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class splitArray {

    private boolean canSplit(int[] nums, int M, int h){

        int sum = 0;
        int cut = 0;
        for(int i = 0, n = nums.length; i < n; i++){

            if(nums[i] > h){
                return false;
            }

            if(sum + nums[i] <= h){
                sum += nums[i];
            }else{
                sum = nums[i];
                cut++;
                if(cut >= M){
                    return false;
                }
            }
        }

        return true;
    }

    public int splitArray(int[] nums, int M) {
        int N = nums.length;
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum += nums[i];
        }

        int left = 0;
        int right = sum;
        while (true){

            if(left == right){
                return left;
            }

            int mid = (left + right) / 2;
            if(canSplit(nums, M, mid)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
    }

    public void test(){
        System.out.println(splitArray(new int[]{7,2,5,10,8}, 2));
    }
}
