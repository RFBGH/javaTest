package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class findWords {

    int[][] move = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    private boolean dfs(boolean[][] gone, char[][] board, String word, int i, int j, int k){

        if(k == word.length()-1){
            return true;
        }

        int n = board.length;
        int m = board[0].length;

        for(int t = 0; t < 4; t++){
            int nextI = i + move[t][0];
            int nextJ = j + move[t][1];

            if(nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= m){
                continue;
            }

            if(gone[nextI][nextJ]){
                continue;
            }

            if(board[nextI][nextJ] != word.charAt(k+1)){
                continue;
            }

            gone[nextI][nextJ] = true;
            if(dfs(gone, board, word, nextI, nextJ, k+1)){
                return true;
            }
            gone[nextI][nextJ] = false;
        }
        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {

        List<String> result = new ArrayList<>();


        int n = board.length;
        int m = board[0].length;
        for(String word : words){

            boolean find = false;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(board[i][j] == word.charAt(0)){
                        boolean[][] gone = new boolean[n][m];
                        gone[i][j] = true;

                        find = dfs(gone, board, word, i, j, 0);
                        if(find){
                            result.add(word);
                            break;
                        }
                    }
                }
                if(find){
                    break;
                }
            }
        }

        return result;
    }

    public void test(){
//        findWords(new char[][]
//                        {
//                                {'o','a','a','n'},
//                                {'e','t','a','e'},
//                                {'i','h','k','r'},
//                                {'i','f','l','v'}
//                        }
//                , new String[]{"oath","pea","eat","rain"});

        findWords(new char[][]
                        {
                                {'a','b'},
                                {'a','a'}
                        }
                , new String[]{"aaba"});
    }

}
