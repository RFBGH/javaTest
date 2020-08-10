package com.rfb.demo.rxjavatest.algorithm.leetCode;

/**
 * Created by Administrator on 2020/8/10 0010.
 */
public class Leet22 {


    public static char[] result = new char[100];
    public static int cur = 0;

    private static void dfs(int leftCount, int rightCount, int n) {

        if(cur == 2*n){

            for(char c:result){
                System.out.print(c);
            }
            System.out.println();
            return;
        }

        if(leftCount < n){
            result[cur++] = '(';
            dfs(leftCount + 1, rightCount, n);
            cur--;
        }

        if(leftCount > rightCount){
            result[cur++] = ')';
            dfs(leftCount, rightCount+1, n);
            cur--;
        }
    }

    public static void test(){

        dfs(0, 0, 4);
    }

}
