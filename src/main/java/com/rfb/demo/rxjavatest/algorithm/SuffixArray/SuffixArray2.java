package com.rfb.demo.rxjavatest.algorithm.SuffixArray;

/**
 * Created by Administrator on 2020/8/14 0014.
 */
public class SuffixArray2 {

    private static void print(int[] a){
        for(int i = 0, size = a.length; i < size; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static int[] suffix(String s){

        int n = s.length();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = s.charAt(i) - 'a';
        }

        int[] sa = new int[n];
        int[] c = new int[n];
        int[] x = new int[1000];
        int[] y = new int[1000];
        for(int i = 0; i < 1000; i++){
            x[i] = -1;
            y[i] = -1;
        }


        for(int i = 0; i < n; i++){
            c[x[i] = a[i]]++;
        }

        for(int i = 1; i < n; i++){
            c[i] += c[i-1];
        }

        for(int i = n-1; i >= 0; i--){
            sa[--c[x[i]]] = i;
        }

        for(int k = 1; k <= n; k <<= 1){

            int cnt = 0;
            for(int i = n-k; i < n; i++){
                y[cnt++] = i;
            }

            for(int i = 0; i < n; i++){
                if(sa[i] >= k){
                    y[cnt++] = sa[i] - k;
                }
            }

            for(int i = 0; i < n; i++){
                c[i] = 0;
            }

            for(int i = 0; i < n; i++){
                c[x[y[i]]]++;
            }

            for(int i = 1; i < n; i++){
                c[i] += c[i-1];
            }

            for(int i = n-1; i >= 0; i--){
                sa[--c[x[y[i]]]] = y[i];
            }

            int[] t = x;
            x = y;
            y = t;

            cnt = 0;
            x[sa[0]] = 0;
            for(int i = 1; i < n; i++){
                if(y[sa[i]] == y[sa[i-1]] && y[sa[i]+k] == y[sa[i-1]+k]){
                    x[sa[i]] = cnt;
                }else{
                    x[sa[i]] = ++cnt;
                }
            }

            print(x);

            for(int i = 0; i < n; i++){
                System.out.print(x[sa[i]]+" ");
            }
            System.out.println();
        }

        return sa;

    }

    public static void test(){
        String s = "aabaaaab";

        int[] sa = suffix(s);
        for(int i = 0; i < s.length(); i++){
            System.out.print(sa[i]+" ");
        }

        System.out.println();
    }

}
