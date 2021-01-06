package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class closestToTarget {

    private int fun(int[] arr, int l, int r){

        if(r < l){
            return -100000000;
        }

        int ans = arr[l];
        for(int i = l+1; i <= r; i++){
            ans = ans & arr[i];
        }
        return ans;
    }

    private int min = Integer.MAX_VALUE;

    private int[] data;
    private int n;

    private void build(int[] data, int k, int l, int r){

        if(l == r){
            return;
        }

        int mid = (l + r) / 2;
        build(data, k*2+1, l, mid);
        build(data, k*2+2, mid+1, r);

        if(data[k*2+1] != -1 && data[k*2+2] != -1){
            data[k] = data[k*2+1] & data[k*2+2];
        }else if(data[k*2+1] != -1){
            data[k] = data[k*2+1];
        }else{
            data[k] = -1;
        }
    }

    public int query(int[] data, int k, int l, int r, int from, int to){

        if(from > r || to < l){
            return -1;
        }

        if(l >= from && r <= to){
            return data[k];
        }

        int mid = (l + r) / 2;
        int left = query(data, k*2+1, l, mid, from, to);
        int right = query(data, k*2+2, mid+1, r, from, to);
        if(left != -1 && right != -1){
            return left & right;
        }
        return left;
    }

    public int closestToTarget(int[] arr, int target) {

        int i;
        for(i = 1; (1 << i) < arr.length; i++);
        n = 1<<i;
        data = new int[n*2-1];

        for(i = 0; i < arr.length; i++){
            data[n-1+i] = arr[i];
        }

        for(i = arr.length; i < n; i++){
            data[n-1+i] = -1;
        }

        build(data, 0, 0, n-1);

        int left = 0;
        int right = 0;
        while (right < arr.length){
            


        }

        return min;
    }

    public void test(){
        System.out.println(closestToTarget(new int[]{70,15,21,96}, 4));
    }
}
