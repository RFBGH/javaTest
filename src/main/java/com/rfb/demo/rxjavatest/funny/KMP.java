package com.rfb.demo.rxjavatest.funny;

/**
 * Created by Administrator on 2017/10/24 0024.
 */
public class KMP {

    public static void findDstInSrc(String src, String dst){

        int[] partialMatchTable = new int[dst.length()];

        partialMatchTable[0] = -1;

        for(int i = 1, size = dst.length(); i < size; i++){

            char c = dst.charAt(i);
            int last = partialMatchTable[i-1];
            char lastIndexNextChar = dst.charAt(last+1);

            int partialMatch = -1;

            while (last != -1){

                if(lastIndexNextChar == c){
                    partialMatch = last+1;
                    break;
                }else{
                    last = partialMatchTable[last];
                }

                lastIndexNextChar = dst.charAt(last+1);
            }

            if(lastIndexNextChar == c){
                partialMatch = last+1;
            }

            partialMatchTable[i] = partialMatch;
        }

        for(int i = 0; i < partialMatchTable.length; i++){
            System.out.print(partialMatchTable[i]+" ");
        }
        System.out.println();

        int iSrc = 0;
        int iDst = 0;


        while(iSrc - iDst + dst.length() < src.length()
                && iDst < dst.length()){


            System.out.println(src);
            for(int i = 0; i < iSrc; i++){
                System.out.print(" ");
            }
            System.out.println("#");

            for(int i = 0; i < iSrc-iDst; i++){
                System.out.print(" ");
            }
            System.out.println(dst);

            for(int i = 0; i < iSrc; i++){
                System.out.print(" ");
            }
            System.out.println("*");

            System.out.println();

            if(src.charAt(iSrc) == dst.charAt(iDst)){
                iDst++;
                iSrc++;
                continue;
            }

            if(iDst != 0){
                iDst = partialMatchTable[iDst-1]+1;
            }else{
                iSrc++;
            }
        }

    }

    public static void test(){

//        findDstInSrc("BBC ABCDAB ABCDABCDABDE", "ABCDABD");

        findDstInSrc("babcbabcabcaabcabcabcacabc", "abcabcacab");

    }

}
