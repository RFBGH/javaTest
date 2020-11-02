package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class IsMatch2 {

    Boolean[][] map;

    private boolean dfs(String s, int sStart, String p, int pStart){

        if(map[sStart][pStart] != null){
            return map[sStart][pStart];
        }

        if(sStart == s.length()){

            boolean result = true;
            for(int i = pStart, size = p.length(); i < size; i++){
                char c = p.charAt(i);
                if(c != '*'){
                    result = false;
                    break;
                }
            }

            map[sStart][pStart] = result;
            return result;
        }

        if(pStart == p.length()){
            map[sStart][pStart] = false;
            return false;
        }

        char c = s.charAt(sStart);
        char pC = p.charAt(pStart);

        boolean result = false;
        if(pC == '*'){
            result = dfs(s, sStart+1, p, pStart);

            if(!result){
                result = dfs(s, sStart+1, p, pStart+1);
            }

            if(!result){
                result = dfs(s, sStart, p, pStart+1);
            }
        }else if(pC == '?' || pC == c){
            result = dfs(s, sStart+1, p, pStart+1);
        }

        map[sStart][pStart] = result;
        return result;
    }

    public boolean isMatch(String s, String p) {

        map = new Boolean[s.length()+5][p.length()+5];
        return dfs(s, 0, p, 0);
    }

    public void test(){

//        System.out.println(isMatch("aa", "a"));
//        System.out.println(isMatch("aa", "*"));
//        System.out.println(isMatch("cb", "?a"));
//        System.out.println(isMatch("adceb", "*a*b"));
//        System.out.println(isMatch("acdcb", "a*c?b"));
        System.out.println(isMatch("aaabababaaabaababbbaaaabbbbbbabbbbabbbabbaabbababab", "*ab***ba**b*b*aaab*b"));
    }
}
