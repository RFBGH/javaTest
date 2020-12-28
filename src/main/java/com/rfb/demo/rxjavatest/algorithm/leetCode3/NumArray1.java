package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class NumArray1 {

    private int sums[];
    private int origin[];

    private int lowBit(int x){
        return x&-x;
    }

    private void add(int i, int val){
        while (i < sums.length){
            sums[i] += val;
            i += lowBit(i);
        }
    }

    public NumArray1(int[] nums) {

        origin = nums;
        sums = new int[nums.length+1];
        for(int i = 0; i < nums.length; i++){
            add(i+1, nums[i]);
        }
    }

    public void update(int i, int val) {

        int old = origin[i];
        origin[i] = val;

        i++;
        int cut = val - old;
        add(i, cut);
    }

    private int sum(int i){

        if(i < 1){
            return 0;
        }

        int sum = 0;
        while (i > 0){
            sum += sums[i];
            i -= lowBit(i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        j++;
        return sum(j) - sum(i);
    }

    public static void test(){

        NumArray1 numArray = new NumArray1(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));

        numArray = new NumArray1(new int[]{-1});
        System.out.println(numArray.sumRange(0, 0));
        numArray.update(0, 1);
        System.out.println(numArray.sumRange(0, 0));

    }
}
