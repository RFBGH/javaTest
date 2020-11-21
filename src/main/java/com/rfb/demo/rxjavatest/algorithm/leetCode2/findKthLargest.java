package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class findKthLargest {

    private int dfs(int[] nums, int k, int from, int to){

        int i = from+1;
        int j = to;

        while (true){

            while (j > from){
                if(nums[j] < nums[from]){
                    break;
                }
                j--;
            }

            while (i <= to){
                if(nums[i] > nums[from]){
                    break;
                }
                i++;
            }

            if(i >= j){
                break;
            }

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }

        int temp = nums[from];
        nums[from] = nums[j];
        nums[j] = temp;

        if(j > k){
            return dfs(nums, k, from, j);
        }else if(j < k){
            return dfs(nums, k, j+1, to);
        }else{
            return nums[j];
        }

    }

    public int findKthLargest(int[] nums, int k) {
        return dfs(nums, nums.length-k, 0, nums.length-1);
    }
    public void test(){
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }
}
