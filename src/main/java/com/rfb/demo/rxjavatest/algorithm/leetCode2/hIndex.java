package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class hIndex {

    private boolean isValid(int[] citations, int h){
        int sum = 0;
        for(int i = 0, size = citations.length; i < size; i++){
            if(citations[i] >= h){
                sum++;
            }
        }

        return sum >= h;
    }

    public int hIndex(int[] citations) {

        if(citations.length == 0){
            return 0;
        }

        int max = 0;
        for(int i = 0, size = citations.length; i < size; i++){
            if(max < citations[i]){
                max = citations[i];
            }
        }

        int left = 0;
        int right = max;
        while (true){

            if(left == right){
                break;
            }

            if(left == right + 1){
                break;
            }

            int mid = (left + right) / 2;
            if(isValid(citations, mid)){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        if(!isValid(citations, left)){
            left--;
        }

        return left;
    }

    public void test(){
        System.out.println(hIndex(new int[]{3, 0, 6, 1, 5}));
    }


}
