package com.rfb.demo.rxjavatest.funny;

import java.util.Collections;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
public class Game1 {

    private int[] array;
    private boolean[] takeable;
    private int result[] = new int[9];

    private boolean isValid(){

        int sum = result[0]+result[1]+result[2];

        if(sum != result[3]+result[4]+result[5]){
            return false;
        }

        if(sum != result[6]+result[7]+result[8]){
            return false;
        }

        if(sum != result[0]+result[3]+result[6]){
            return false;
        }

        if(sum != result[1]+result[4]+result[7]){
            return false;
        }

        if(sum != result[2]+result[5]+result[8]){
            return false;
        }

        if(sum != result[0]+result[4]+result[8]){
            return false;
        }

        if(sum != result[2]+result[4]+result[6]){
            return false;
        }

        return true;

    }

    private void findResult(int index){

        if(index == 9){

            if(isValid()){

                System.out.print("result:");
                for(int i = 0, size = result.length; i < size; i++){
                    if(i%3 == 0){
                        System.out.println();
                    }
                    System.out.print(result[i]+" ");
                }
                System.out.println();
                System.out.println();
            }
            return;
        }

        for(int i = 0, size = takeable.length; i < size; i++){

            if(takeable[i]){
                result[index] = array[i];
                takeable[i] = false;
                findResult(index+1);
                takeable[i] = true;
            }
        }


    }

    public void test(){

        array = new int[]{15, 16, 17, 18, 19, 20, 21, 22, 23};
        takeable = new boolean[array.length];
        for(int i = 0, size = takeable.length; i < size; i++){
            takeable[i] = true;
        }

        findResult(0);

    }

}
