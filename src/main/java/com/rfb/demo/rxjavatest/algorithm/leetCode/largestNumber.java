package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.*;

public class largestNumber {

    public String largestNumber(int[] nums) {

        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        for(int i = 0,size = nums.length; i < size; i++){
            list.add(nums[i]);
        }

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                int a = o1;
                List<Integer> t1 = new ArrayList<>();
                if(a == 0){
                    t1.add(a);
                }else{
                    while (a != 0){
                        t1.add(a % 10);
                        a /= 10;
                    }
                }
                Collections.reverse(t1);

                int b = o2;
                List<Integer> t2 = new ArrayList<>();
                if(b == 0){
                    t2.add(b);
                }else{
                    while (b != 0){
                        t2.add(b % 10);
                        b /= 10;
                    }
                }
                Collections.reverse(t2);

                int i = 0;
                int j = 0;
                int size1 = t1.size();
                int size2 = t2.size();

                while (true){
                    if(t1.get(i) > t2.get(j)){
                        return -1;
                    }

                    if(t1.get(i) < t2.get(j)){
                        return 1;
                    }
                    i++;
                    j++;

                    if(i == size1 && j == size2){
                        break;
                    }

                    if(i == size1){
                        i = 0;
                    }
                    if(j == size2){
                        j = 0;
                    }
                }

                return 0;

            }
        });

        if(list.get(0) == 0){
            return "0";
        }

        for(Integer i : list){
            sb.append(i);
        }

        return sb.toString();
    }

    public void test(){
        System.out.println(largestNumber(new int[]{1,2,3,4,5,6,7,8,9,0}));
//        System.out.println(largestNumber(new int[]{3,30,34,5,9}));
//        System.out.println(largestNumber(new int[]{30, 3}));
    }



}
