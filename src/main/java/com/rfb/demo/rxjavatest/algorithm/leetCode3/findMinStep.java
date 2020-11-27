package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class findMinStep {

    char[] type = new char[]{'R','Y','B','G','W'};

    private int dfs(String board, int[] handCount){

        if(board.length() == 0){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++){
            if(handCount[i] == 0){
                continue;
            }

            char c = type[i];
            handCount[i]--;
            for(int j = 0; j <= board.length(); j++){
                StringBuilder tempBoard = new StringBuilder(board);
                if(j == board.length()){
                    tempBoard.append(c);
                }else{
                    tempBoard.insert(j, c);
                }

                int check = j;
                while (true){

                    int left;
                    for(left = check-1; left >= 0; left--){
                        if (tempBoard.charAt(left) != tempBoard.charAt(check)) {
                            break;
                        }
                    }

                    int right;
                    for(right = check+1; right < tempBoard.length(); right++){
                        if(tempBoard.charAt(right) != tempBoard.charAt(check)){
                            break;
                        }
                    }

                    if(right - left - 1 >= 3){
                        tempBoard.replace(left+1, right, "");
                        check = left;
                        if(check < 0){
                            check = 0;
                        }
                    }else{
                        break;
                    }
                }

                int temp = dfs(tempBoard.toString(), handCount);
                if(min > temp){
                    min = temp;
                }

                if(min == 0){
                    break;
                }
            }
            handCount[i]++;
        }

        if(min == Integer.MAX_VALUE){
            return min;
        }
        return min+1;
    }

    public int findMinStep(String board, String hand) {

        int[] handCount = new int[5];
        for(int i = 0; i < hand.length(); i++){
            char c = hand.charAt(i);
            if(c == 'R'){
                handCount[0]++;
            }else if(c == 'Y'){
                handCount[1]++;
            }else if(c == 'B'){
                handCount[2]++;
            }else if(c == 'G'){
                handCount[3]++;
            }else{
                handCount[4]++;
            }
        }

        int result = dfs(board, handCount);
        if(result == Integer.MAX_VALUE){
            return -1;
        }
        return result;
    }

    public void test(){
        System.out.println(findMinStep("RBYYBBRRB", "YRBGB"));
    }
}
