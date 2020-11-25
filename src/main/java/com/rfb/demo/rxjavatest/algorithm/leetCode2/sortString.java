package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class sortString {

    public String sortString(String s) {

        StringBuilder temp = new StringBuilder(s);

        StringBuilder sb = new StringBuilder();
        while (temp.length() != 0){

            char lastC = 0;
            while (true){

                char min = 256;
                int index = -1;

                for(int i = 0, size = temp.length(); i < size; i++){
                    char c = temp.charAt(i);
                    if(c > lastC && min > c){
                        min = c;
                        index = i;
                    }
                }

                if(min == 256){
                    break;
                }

                lastC = min;
                temp.deleteCharAt(index);
                sb.append(lastC);
            }

            lastC = 256;
            while (true){

                char max = 0;
                int index = -1;
                for(int i = 0, size = temp.length(); i < size; i++){
                    char c = temp.charAt(i);
                    if(c < lastC && max < c){
                        max = c;
                        index = i;
                    }
                }

                if(max == 0){
                    break;
                }

                lastC = max;
                temp.deleteCharAt(index);
                sb.append(lastC);
            }
        }

        return sb.toString();
    }

    public void test(){
        System.out.println(sortString("aaaabbbbcccc"));
    }

}
