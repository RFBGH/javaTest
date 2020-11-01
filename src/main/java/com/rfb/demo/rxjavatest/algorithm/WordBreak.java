package com.rfb.demo.rxjavatest.algorithm;

import java.util.*;

public class WordBreak {

    private static class DicTree{

        char c;
        boolean isWord;
        Map<Character, DicTree> sons = new HashMap<>();

        public DicTree(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
        }

        public void addSon(DicTree son){
            sons.put(son.c, son);
        }
    }

    private void addWord(DicTree root, String word){

        DicTree cur = root;

        for(int i = 0, size = word.length(); i < size; i++){

            char c = word.charAt(i);
            DicTree next = cur.sons.get(c);

            boolean isWord = i == size-1;

            if(next == null){
                next = new DicTree(c, isWord);
                cur.addSon(next);
            }else{
                if(isWord){
                    next.isWord = true;
                }
            }
            cur = next;
        }
    }

    private Map<Integer, List<List<String>>> map = new HashMap<>();

    private List<List<String>> dfs(DicTree root, String word, String s, int start){

        List<List<String>> result;
        if(start == s.length()){
            List<String> words = new ArrayList<>();
            words.add(word);
            result = new ArrayList<>();
            result.add(words);
            map.put(start-word.length(), result);
            return result;
        }

        result = map.get(start);
        if(result != null){
            result = new ArrayList<>(result);
        }

        if(result == null){

            result = new ArrayList<>();
            DicTree curDic = root;
            for(int i = start, size = s.length(); i < size; i++){

                char c = s.charAt(i);
                DicTree nextDic = curDic.sons.get(c);
                if(nextDic == null){
                    break;
                }

                if(nextDic.isWord){
                    List<List<String>> temp = dfs(root, s.substring(start, i+1), s, i+1);
                    result.addAll(temp);
                }
                curDic = nextDic;
            }
        }

        if(word != null){

            List<List<String>> temp = result;
            result = new ArrayList<>();

            for(List<String> list : temp){
                List<String> t = new ArrayList<>(list);
                t.add(0, word);
                result.add(t);
            }
            map.put(start-word.length(), result);
        }

        return result;
    }

    public List<String> wordBreak(String s, List<String> wordDict) {

        DicTree root = new DicTree('#', false);
        for(String word : wordDict){
            addWord(root, word);
        }


        List<String> sentences = new ArrayList<>();
        List<List<String>> result = dfs(root, null, s, 0);
        for(List<String> list : result){

            StringBuilder sb = new StringBuilder();
            for(int i = 0, size = list.size(); i < size; i++){
                sb.append(list.get(i));
                if(i != size-1){
                    sb.append(" ");
                }
            }
            sentences.add(sb.toString());
        }

        return sentences;
    }

    public void test(){

//        String[] words = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
//        List<String> wordDict = new ArrayList<>(Arrays.asList(words));
////        List<String> wordDict = new ArrayList<>();
////        wordDict.add("cat");
////        wordDict.add("cats");
////        wordDict.add("and");
////        wordDict.add("sand");
////        wordDict.add("dog");
//
////        List<String> sentences = wordBreak("catsanddog", wordDict);
//        List<String> sentences = wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", wordDict);
//        for(String s : sentences){
//            System.out.println(s);
//        }"aaaaaaa"
//["aaaa","aa","a"]

        String[] words = new String[]{"aaaaa","aa","a"};
        List<String> wordDict = new ArrayList<>(Arrays.asList(words));
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("cat");
//        wordDict.add("cats");
//        wordDict.add("and");
//        wordDict.add("sand");
//        wordDict.add("dog");

//        List<String> sentences = wordBreak("catsanddog", wordDict);
        List<String> sentences = wordBreak("aaaaaaa", wordDict);
        for(String s : sentences){
            System.out.println(s);
        }
    }
}
