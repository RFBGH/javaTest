package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class slidingPuzzle {

    private static class Node{
        public int [] board = new int[6];
        public int step = 0;
        public Node pre;

        public Node(int[][] board) {
            for(int i = 0; i < 6; i++){
                this.board[i] = board[i/3][i%3];
            }
        }

        public Node(int[] board, int step, Node pre) {
            for(int i = 0; i < 6; i++){
                this.board[i] = board[i];
            }
            this.step = step;
            this.pre = pre;
        }

        public int getZeroPos(){
            for(int i = 0; i < 6; i++){
                if (this.board[i] == 0) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Arrays.equals(board, node.board);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(board);
        }
    }

    private int[] move = new int[]{-1, 1, -3, 3};

    public int slidingPuzzle(int[][] board) {

        int i;
        for(i = 0; i < 5; i++){
            if (board[i/3][i%3] != i+1) {
                break;
            }
        }
        if(i == 5){
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Node> gone = new HashSet<>();

        Node node = new Node(board);
        gone.add(node);
        queue.offer(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int pos = cur.getZeroPos();
            for(i = 0; i < 4; i++){
                int nextPos = pos + move[i];
                if(nextPos < 0 || nextPos > 5){
                    continue;
                }
                if(pos == 2 && nextPos == 3 || pos == 3 && nextPos == 2){
                    continue;
                }

                node = new Node(cur.board, cur.step+1, cur);
                int temp = node.board[nextPos];
                node.board[nextPos] = 0;
                node.board[pos] = temp;
                if(gone.contains(node)){
                    continue;
                }

                int k;
                for(k = 0; k < 5; k++){
                    if(node.board[k] != k+1){
                        break;
                    }
                }
                if(k == 5){

//                    Node n = node;
//                    while (n != null){
//
//                        for(int j = 0; j < 6; j++){
//                            System.out.print(n.board[j]);
//                        }
//                        System.out.println();
//
//                        n = n.pre;
//                    }

                    return node.step;
                }

                gone.add(node);
                queue.offer(node);
            }
        }

        return -1;
    }

    public void test(){
        System.out.println(slidingPuzzle(new int[][]{{3,2,4},{1,5,0}}));
    }
}
