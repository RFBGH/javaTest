package com.rfb.demo.rxjavatest.funny;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2019/2/28 0028.
 */
public class BinaryInsert {

    public static int findInsertIndex(int value, List<Integer>list){

        if(list.isEmpty()){
            return 0;
        }

        if(list.size() == 1){
            if(list.get(0) > value){
                return 0;
            }else{
                return 1;
            }
        }

        int left = 0;
        int right = list.size()-1;

        while(left < right){

            int mid = (left + right)/2;
            int midValue = list.get(mid);
            if(value > midValue){
                left = mid+1;
            }else if(value < midValue){
                right = mid;
            }else{
                return mid;
            }
        }

        int leftValue = list.get(left);
        if(value < leftValue){
            return left;
        }
        return left+1;

    }

    public static void test(){

        Random random = new Random();
        List<Integer> list = new ArrayList<Integer>();

        for(int test = 0; test < 10; test++){

            int value = random.nextInt() % 100;
            int index = findInsertIndex(value, list);
            list.add(index, value);

            for(int i:list){
                System.out.print(i+" ");
            }
            System.out.println();
        }

    }
}
