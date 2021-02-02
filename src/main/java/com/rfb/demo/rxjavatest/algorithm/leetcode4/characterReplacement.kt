package com.rfb.demo.rxjavatest.algorithm.leetcode4

class characterReplacement {

    fun characterReplacement(s: String, k: Int): Int {

        var max = 0
        for(i in 0..25){

            val c = 'A' + i

            var rest = k
            var left = 0
            var right = 0
            while(right < s.length){
                if(s[right] == c){
                    right++
                }else{
                    if(rest > 0){
                        rest--
                        right++
                    }else{
                        if(s[left]  != c){
                            rest++
                        }
                        left++
                    }
                }

                if((right - left) > max){
                    max = right - left
                }
            }
        }
        return max
    }
    fun test(){
        println(characterReplacement("AABABBA", 1));
    }
}