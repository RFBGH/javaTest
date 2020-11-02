package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class SolveSudoku {

    private boolean dfs(char[][] board, int i, int j){

        if(i == 9){
            return true;
        }

        char c = board[i][j];
        if(c != '.'){
            int nextJ = j+1;
            int nextI = i;
            if(nextJ == 9){
                nextI = i+1;
                nextJ = 0;
            }
            return dfs(board, nextI, nextJ);
        }else{

            boolean[] valid = new boolean[9];
            for(int k = 0; k < 9; k++){
                valid[k] = true;
            }

            for(int k = 0; k < 9; k++){
                char t = board[i][k];
                if(t != '.'){
                    valid[t-'1'] = false;
                }

                t = board[k][j];
                if(t != '.'){
                    valid[t-'1'] = false;
                }
            }

            int ki = (i / 3) * 3;
            int kj = (j / 3) * 3;
            for(int k = 0; k < 9; k++){
                char t = board[ki + k / 3][kj + k % 3];
                if(t == '.'){
                    continue;
                }
                valid[t-'1'] = false;
            }

            int nextJ = j+1;
            int nextI = i;
            if(nextJ == 9){
                nextI = i+1;
                nextJ = 0;
            }
            for(int k = 0; k < 9; k++){
                if(!valid[k]){
                    continue;
                }

                board[i][j] = (char)('1'+k);
                if(dfs(board, nextI, nextJ)){
                    return true;
                }
            }

            board[i][j] = '.';
            return false;
        }
    }

    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }

    public void test(){

        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','6'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        System.out.println();

        solveSudoku(board);

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
