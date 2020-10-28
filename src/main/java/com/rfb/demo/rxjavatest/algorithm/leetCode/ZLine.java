package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class ZLine {

    public String convert(String s, int numRows) {

        if(s.length() == 1 || numRows == 1){
            return s;
        }

        int size = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numRows; i++){

            int offset = numRows+numRows-2;
            if(i > 0 && i < numRows-1){
                offset -= 2*i;
            }

            int k = 0;
            int odd = 0;
            while (k + i < size){

                sb.append(s.charAt(k + i));

                int nextOffset = numRows+numRows-2-offset;
                if(odd % 2 == 0 || nextOffset == 0){
                    nextOffset = offset;
                }

                if(nextOffset == 0){
                    break;
                }

                k += nextOffset;

                odd = (odd + 1) % 2;
            }
        }
        return sb.toString();
    }

    public void test(){

        System.out.println(convert("LEETCODEISHIRING", 3));

//        System.out.println(convert("PAYPALISHIRING", 4));

    }

}
