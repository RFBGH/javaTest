package com.rfb.demo.rxjavatest.algorithm.SuffixArray;

/**
 * Created by Administrator on 2020/8/12 0012.
 */
public class SuffixArray {

    private static void print(int[] a){
        for(int i = 0, size = a.length; i < size; i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
    }

    public static void calc(String s){

        int n = s.length();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = s.charAt(i) - 'a';
        }

        int[] x = new int[n+100];
        for(int i = 0; i < x.length; i++){
            x[i] = -1;
        }

        int[] c = new int[n];
        for(int i = 0; i < n; i++){
            x[i] = a[i];
            c[x[i]]++;
        }

        print(c);

        for(int i = 1; i < n; i++){
            c[i] += c[i-1];
        }

        print(c);

        int [] sa = new int[n];
        for(int i = n-1; i >= 0; i--){
            sa[--c[x[i]]] = i;
        }

        print(sa);
        print(x);

        for(int i = 0; i < n; i++){
            System.out.print(x[sa[i]]+" ");
        }
        System.out.println();

        int[] y = new int[n+100];
        for(int i = 0; i < y.length; i++){
            y[i] = -1;
        }
        for(int k = 1; k <= n; k <<= 1){

            System.out.println(k+" ");

            int num = 0;
            for(int i = n-k; i < n; i++){
                y[num++] = i;
            }

            for(int i = 0; i < n; i++){
                if(sa[i] >= k){
                    y[num++] = sa[i]-k;
                }
            }

            print(y);
            for(int i = 0; i < n; i++){
                System.out.print(x[y[i]]+" ");
            }
            System.out.println();

            for(int i = 0; i < n; i++){
                c[i] = 0;
            }

            for(int i = 0; i < n; i++){
                c[x[y[i]]]++;
            }

            for(int i = 1; i < n; i++){
                c[i] += c[i-1];
            }
//
            for(int i = n-1; i >= 0; i--){
                sa[--c[x[y[i]]]] = y[i];
            }

            print(sa);

            int[] t = x;
            x = y;
            y = t;

            for(int i = 0; i < n; i++){
                System.out.print(y[i]+" ");
            }
            System.out.println();

            for(int i = 0; i < n; i++){
                System.out.print(y[sa[i]]+" ");
            }
            System.out.println();

            int cnt = 0;
            x[sa[0]] = 0;
            for(int i = 1; i < n; i++){
                x[sa[i]] = (y[sa[i]] == y[sa[i-1]] && y[sa[i]+k] == y[sa[i-1]+k])?cnt:++cnt;
            }

            print(x);
        }

        System.out.print("result:");
        for(int i = 0; i < n; i++){
            System.out.print(sa[i]+" ");
        }
        System.out.println();
    }

    public static void test(){

        String s = "aabaaaab";
        calc(s);

    }

}
