package com.rfb.demo.rxjavatest.algorithm.stack;

import java.util.Random;
import java.util.Stack;

/**
 * Created by Administrator on 2020/8/6 0006.
 */
public class Test {

    public static void test(){

        int n = 20;
        int a[] = new int[n];
        Random random = new Random();
        for(int i = 0; i < n; i++){
            a[i] = random.nextInt(20);
        }
        for(int i = 0; i < n; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();

        int[] L = new int[n];
        for(int i = n-1; i >= 0; i--){
            int cur = a[i];

            int k;
            for(k = i-1; k >= 0; k--){
                if(a[k] < cur){
                    break;
                }
            }

            L[i] = k;
        }

        for(int i = 0; i < n; i++){
            System.out.print(L[i]+" ");
        }
        System.out.println();

        Stack<Integer> stack = new Stack<>();

        int[] L2 = new int[n];
        for(int i = 0; i < n; i++){
            while (!stack.isEmpty()){
                int cur = stack.peek();
                if(a[cur] >= a[i]){
                    stack.pop();
                }else{
                    break;
                }
            }

            if(stack.isEmpty()){
                L2[i] = -1;
            }else{
                L2[i] = stack.peek();
            }
            stack.push(i);
//            L[i]=t==0?-1:st[t-1];
//            st[t++]=i;
        }

        for(int i = 0; i < n; i++){
            System.out.print(L2[i]+" ");
        }
        System.out.println();
    }
}
