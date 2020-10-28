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

        if(left == right){
            if(num[left] < target){
                return left;
            }
            return -1;
        }

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

    private double getResult(int[] nums1, int[] nums2, int target, int seek2){

        int size = nums1.length + nums2.length;
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

        int left1 = 0;
        int right1 = nums1.length - 1;

        int left2 = 0;
        int right2 = nums2.length - 1;


        int size = nums1.length + nums2.length;
        int mid = size / 2;

        while (true){

            int mid2 = (left2 + right2) / 2;
            int seek1 = getLessIndex(nums1, left1, right1, nums2[mid2]);

            if(seek1 == -1){
                left2 = mid2+1;
                continue;
            }

            if(mid2 + seek1 == mid){
//                if(size % 2 == 1){
//                    int x = Integer.MAX_VALUE;
//                    if(mid2 > 0){
//                        x = nums2[mid2-1];
//                    }
//
//                    if(seek1 > 0 && nums1[seek1-1] > x){
//                        x = nums1[seek1-1];
//                    }
//                    return x;
//                }else{
//
//                    int x = Integer.MAX_VALUE;
//                    int y =
//                    if(mid2 > 0){
//                        x = nums2[mid2-1];
//                    }
//
//                    if(seek1 > 0 && nums1[seek1-1] > x){
//                        x = nums1[seek1-1];
//                    }
//
//                }
            }else if(mid2 + seek1 == mid-1){
                if(size % 2 == 1){
                    return nums2[mid2];
                }else{
                    int x = nums2[mid2];
                    int y = Integer.MAX_VALUE;
                    if(mid2 < nums2.length-1){
                        y = nums2[mid2+1];
                    }
                    if(seek1 < nums1.length-1 && y > nums1[seek1+1]){
                        y = nums1[seek1+1];
                    }
                    return (x + y) / 2f;
                }
            }if(mid2 + seek1 == mid-2){

                if(size % 2 == 1){
                    int x = Integer.MAX_VALUE;
                    if(mid2 < nums2.length-1){
                        x = nums2[mid2+1];
                    }

                    if(seek1 < nums1.length - 1 && x > nums1[seek1+1]){
                        x = nums1[seek1+1];
                    }
                    return x;
                }else{
                    int x = Integer.MAX_VALUE;
                    int y = Integer.MAX_VALUE;
                    if(mid2 < nums2.length-1){
                        x = nums2[mid2+1];
                        if(mid2 < nums2.length - 2){
                            y = nums2[mid2+2];
                        }
                    }

                    if(seek1 < nums1.length - 1 && x > nums1[seek1+1]){
                        y = x;
                        x = nums1[seek1+1];
                        if(seek1 < nums1.length - 2 && y > nums1[seek1+2]){
                            y = nums1[seek1+2];
                        }
                    }
                    return (x + y) / 2f;
                }
            }else if(mid2 + seek1 > mid-2){
                right1 = seek1;
                right2 = mid2;
            }else{
                left1 = seek1 + 1;
                left2 = mid2 + 1;
            }
        }
    }

    public void test(){

//        System.out.println(findMedianSortedArrays(new int[]{1,2,4,7}, new int[]{3,5,6}));

//        System.out.println(findMedianSortedArrays(new int[]{3,5,6}, new int[]{1,2,4,7} ));

//        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2,4}));

        System.out.println(findMedianSortedArrays(new int[]{2,4}, new int[]{1,3}));
//
//        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1}));
//        System.out.println(findMedianSortedArrays(new int[]{2}, new int[]{}));

    }
}
