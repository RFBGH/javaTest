package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class sortString1 {

    public String sortString(String s) {
        int[] buck = new int[26];

        for(int i = 0, size = s.length(); i <size; i++){
            buck[s.charAt(i)-'a']++;
        }

        StringBuilder sb = new StringBuilder();

        boolean finish;
        while (true){

            finish = true;
            for(int i = 0; i < 26; i++){
                if(buck[i] == 0){
                    continue;
                }

                buck[i]--;
                sb.append((char)('a'+i));
                finish = false;
            }

            if(finish){
                break;
            }

            finish = true;
            for(int i = 25; i >= 0; i--){
                if(buck[i] == 0){
                    continue;
                }
                buck[i]--;
                sb.append((char)('a'+i));
                finish = false;
            }

            if(finish){
                break;
            }
        }

        return sb.toString();
    }

    public void test(){
        System.out.println(sortString("aaaabbbbcccc"));
    }

}
