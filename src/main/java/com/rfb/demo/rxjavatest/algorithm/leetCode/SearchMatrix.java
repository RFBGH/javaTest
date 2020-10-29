package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix.length == 0 || matrix[0].length == 0){
            return false;
        }

        int top = 0;
        int bottom = matrix.length-1;
        int mid;
        while (true){

            if(bottom == top){
                break;
            }

            mid = (top + bottom) / 2;
            if(matrix[mid][0] == target){
                return true;
            }

            if(matrix[mid][0] > target){
                bottom = mid;
            }else{
                top = mid+1;
            }
        }

        if(matrix[top][0] > target){
            top--;
        }

        if(top < 0){
            return false;
        }

        int left = 0;
        int right = matrix[0].length;

        while (true){

            mid = (left + right) / 2;
            if(mid < matrix[top].length && matrix[top][mid] == target){
                return true;
            }

            if(left == right){
                return false;
            }

            if(matrix[top][mid] > target){
                right = mid;
            }else{
                left = mid+1;
            }
        }
    }

    public void test(){

//        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 3));
//        System.out.println(searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 13));

        System.out.println(searchMatrix(new int[][]{{1}}, 1));

    }

}
