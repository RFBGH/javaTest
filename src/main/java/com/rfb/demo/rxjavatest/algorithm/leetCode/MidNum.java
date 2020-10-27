package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class MidNum {

//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//        int[] nums = new int[nums1.length+nums2.length];
//        int i = 0;
//        int i1 = 0;
//        int i2 = 0;
//
//        while (i1 < nums1.length && i2 < nums2.length){
//            if(nums1[i1] < nums2[i2]){
//                nums[i++] = nums1[i1++];
//            }else{
//                nums[i++] = nums2[i2++];
//            }
//        }
//
//        while (i1 < nums1.length){
//            nums[i++] = nums1[i1++];
//        }
//
//        while (i2 < nums2.length){
//            nums[i++] = nums2[i2++];
//        }
//
//        int mid = nums.length / 2;
//        if(nums.length % 2 == 0){
//            return (nums[mid-1]+nums[mid])/2f;
//        }else{
//            return nums[mid];
//        }
//    }

//

    private int getLessIndex(int[] num, int left, int right, int target){
        int mid = (left + right) / 2;
        while (true){

            if(target > num[mid]){
                left = mid + 1;
            }else{
                right = mid;
            }

            if(left == right){

                if(num[left] < target){
                    return left;
                }

                return left-1;
            }

            mid = (left + right) / 2;
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1.length == 0 || nums2.length == 0){

            int[] num = nums1;
            if(num.length == 0){
                num = nums2;
            }

            int mid = num.length / 2;
            if(num.length % 2 == 1){
                return num[mid];
            }else{
                return (num[mid]+num[mid-1])/2f;
            }
        }

        int size = nums1.length + nums2.length;
        int mid = size / 2;

        if(nums2[0] > nums1[nums1.length-1] || nums1[0] > nums2[nums2.length-1]){
            int[] numX = nums1;
            int[] numY = nums2;
            if(nums1[0] > nums2[nums1.length-1]){
                numX = nums2;
                numY = nums1;
            }

            if(size % 2 == 1){
                if(mid < numX.length){
                    return numX[mid];
                }
                mid -= numX.length;
                return numY[mid];
            }else{

                int x;
                int y;
                if(mid < numX.length){
                    x = numX[mid];
                    y = numX[mid-1];
                }else{
                    mid -= numX.length;
                    x = numY[mid];
                    if(mid == 0){
                        y = numX[numX.length-1];
                    }else{
                        y = numY[mid-1];
                    }
                }
                return (x + y)/2f;
            }
        }

        int seek1 = nums1.length-1;
        int seek2 = nums2.length-1;


        while (true){

            if(nums1[seek1] > nums2[seek2]){

                int newSeek1 = getLessIndex(nums1, 0, seek1, nums2[seek2]);
                if(newSeek1 == -1){
                    mid -=
                }

                if(newSeek1 + seek2 < mid){
                    int target = mid - newSeek1 - seek2;

                    if(size % 2 == 1){
                        return nums1[target];
                    }else{
                        int x = nums1[target];
                        int y;
                        if(target == 0){
                            y = nums2[seek2];
                        }else{
                            y = nums1[target-1];
                        }
                        return (x + y) / 2f;
                    }
                }

                seek1 = newSeek1;
            }else{
                int lessIndexInNum2 = getLessIndex(nums2, 0, mid2, nums1[mid1]);
                mid2 = lessIndexInNum2;
            }

        }

        return 0;
    }

    public void test(){

        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));

        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2,4}));

        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1}));
        System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{}));

    }
}
