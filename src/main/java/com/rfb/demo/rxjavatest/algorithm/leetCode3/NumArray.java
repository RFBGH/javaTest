package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class NumArray {

    private int sum[];
    private int n;
    private int h;

    private void build(int i, int l, int r, int num[]){

        if(l == r){
            if(l < num.length){
                sum[i] = num[l];
            }
            return;
        }

        int left = i*2+1;
        int mid = (l + r) / 2;
        build(left, l, mid, num);

        int right = i*2+2;
        build(right, mid+1, r, num);

        sum[i] = sum[left]+sum[right];
    }

    public NumArray(int[] nums) {
        int all = 0;
        for(h = 0; (1 << h) < nums.length; h++){
            all += (1<<h);
        }
        n = (1 << h);
        all += n;
        sum = new int[all];

        build(0, 0, n-1, nums);
    }

    public void update(int i, int val) {

        int index = n-1+i;
        int temp = sum[index];
        sum[index] = val;

        if(index == 0){
            return;
        }

        while (true){
            index = (index-1)/2;
            sum[index] -= temp;
            sum[index] += val;

            if(index == 0){
                break;
            }
        }
    }

    private int query(int i, int l, int r, int f, int t){

        if(l >= f && r <= t){
            return sum[i];
        }

        if(f > r || l > t){
            return 0;
        }

        int left = i*2+1;
        int mid = (l + r) / 2;
        int right = i*2+2;
        return query(left, l, mid, f, t) + query(right, mid+1, r, f, t);
    }

    public int sumRange(int i, int j) {
        return query(0, 0, n-1, i, j);
    }

    public static void test(){

//        NumArray numArray = new NumArray(new int[]{1, 3, 5});
//        System.out.println(numArray.sumRange(0, 2));
//        numArray.update(1, 2);
//        System.out.println(numArray.sumRange(0, 2));

        NumArray numArray = new NumArray(new int[]{-1});
        System.out.println(numArray.sumRange(0, 0));
        numArray.update(0, 1);
        System.out.println(numArray.sumRange(0, 0));

    }
}
