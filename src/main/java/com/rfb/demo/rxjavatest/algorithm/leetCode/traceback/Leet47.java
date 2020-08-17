package com.rfb.demo.rxjavatest.algorithm.leetCode.traceback;

/**
 * Created by Administrator on 2020/8/17 0017.
 */
public class Leet47 {

    private static int[] a = null;
    private static int n;
    private static int[] result = null;
    private static int resultCount = 0;
    private static boolean[] used = null;

    public static void dfs(int last){

        if(resultCount == n){

            for(int i = 0; i < resultCount; i++){
                System.out.print(result[i]+" ");
            }
            System.out.println();
            return;
        }

        for(int i = 0 ; i < n; i++){

            if(used[i]){
                continue;
            }

            if(i > 0 && a[i] == a[i-1] && used[i-1]){
                break;
            }

            used[i] = true;
            result[resultCount++] = a[i];
            dfs(i);
            resultCount--;
            used[i] = false;
        }

    }

    public static void test(){

        a = new int[]{1, 1, 1, 2, 4};  //need sort
        n = a.length;
        result = new int[n];
        used = new boolean[n];

        dfs(-1);

    }
}
