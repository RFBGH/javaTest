package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {

    List<List<String>> result = new ArrayList<>();

    private void dfs(int[][]map, int n, int i){

        if(i == n){

            List<String> temp = new ArrayList<>();
            for(int ki = 0; ki < n; ki++){
                StringBuilder sb = new StringBuilder();
                for(int kj = 0; kj < n; kj++){
                    if(map[ki][kj] == 1){
                        sb.append("Q");
                    }else{
                        sb.append(".");
                    }
                }
                temp.add(sb.toString());
            }

            result.add(temp);
            return;
        }

        boolean[] valid = new boolean[n];
        for(int k = 0; k < n; k++){
            valid[k] = true;
        }

        for(int j = 0;  j < n; j++){
            boolean queenable = true;
            for(int k = 0; k < i; k++){
                if(map[k][j] == 1){
                    queenable = false;
                    break;
                }
            }

            if(queenable){
                int startI = i+1;
                int startJ = j+1;

                while (startI < n && startJ < n){

                    if(map[startI][startJ] == 1){
                        queenable = false;
                        break;
                    }

                    startI++;
                    startJ++;
                }

                if(queenable){
                    startI = i+1;
                    startJ = j-1;
                    while (startI < n && startJ >= 0){

                        if(map[startI][startJ] == 1){
                            queenable = false;
                            break;
                        }

                        startI++;
                        startJ--;
                    }
                }

                if(queenable){
                    startI = i-1;
                    startJ = j+1;
                    while (startI >= 0 && startJ < n){

                        if(map[startI][startJ] == 1){
                            queenable = false;
                            break;
                        }

                        startI--;
                        startJ++;
                    }
                }

                if(queenable){
                    startI = i-1;
                    startJ = j-1;
                    while (startI >= 0 && startJ >= 0){

                        if(map[startI][startJ] == 1){
                            queenable = false;
                            break;
                        }

                        startI--;
                        startJ--;
                    }
                }
            }

            valid[j] = queenable;
        }

        for(int j = 0; j < n; j++){
            if(!valid[j]){
                continue;
            }
            map[i][j] = 1;
            dfs(map, n, i+1);
            map[i][j] = 0;
        }
    }

    public List<List<String>> solveNQueens(int n) {

        int[][] map = new int[n][n];
        dfs(map, n, 0);
        return result;
    }

    public void test(){

        solveNQueens(4);
        for(List<String> list:result){
            for(String s : list){
                System.out.println(s);
            }
            System.out.println();
        }
    }
}
