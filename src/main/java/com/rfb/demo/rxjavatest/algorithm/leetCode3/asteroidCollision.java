package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.List;

public class asteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {

        List<Integer> list = new ArrayList<>();
        for(int i: asteroids){
            list.add(i);
        }

        while (!list.isEmpty()){

            boolean finish = true;

            int first = list.get(0);
            for(int i = 1; i < list.size(); i++){

                if(first == 1001){
                    first = list.get(i);
                    continue;
                }

                int second = list.get(i);
                if((first * second >= 0) || (first < 0 && second > 0)){
                    first = second;
                }else {

                    if(Math.abs(first) == Math.abs(second)){
                        list.remove(i-1);
                        list.remove(i-1);
                        i-=2;
                        if(i >= 0){
                            first = list.get(i);
                        }else{
                            first = 1001;
                        }
                    }else if(Math.abs(first) > Math.abs(second)){
                        list.remove(i);
                        i-=1;
                    }else{
                        list.remove(i-1);
                        i-=1;
                        first = second;
                    }
                    finish = false;
                }

            }

            if(finish){
                break;
            }
        }

        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            result[i] = list.get(i);
        }

        return result;
    }

    public void test(){
        int[] result = asteroidCollision(new int[]{-2,1,-2,-2});
        for(int i = 0; i < result.length; i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
    }
}
