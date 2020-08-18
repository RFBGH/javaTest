package com.rfb.demo.rxjavatest.algorithm.leetCode.traceback;

/**
 * Created by Administrator on 2020/8/17 0017.
 */
public class Leet90 {

    private static int[] a = null;
    private static int n;
    private static int[] result = null;
    private static int resultCount = 0;
    private static boolean[] used = null;

    private static void dfs(int count){

        if(count == resultCount){

            for(int i = 0; i < resultCount; i++){
                System.out.print(result[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i = 0; i < n; i++){
            if(used[i]){
                continue;
            }

            if(i > 0 && a[i] == a[i-1] && !used[i-1]){
                continue;
            }

            used[i] = true;
            result[resultCount++] = a[i];
            dfs(count);
            resultCount--;
            used[i] = false;
        }
    }

    public static void test(){

        a = new int[]{1, 2, 2};  //need sort
        n = a.length;
        result = new int[n];
        used = new boolean[n];

        for(int i = 1; i <= n; i++){
            dfs(i);
        }
    }

}
