package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class findMinStep1 {

    private static class Node{
        String board;
        String hand;
        int step;

        public Node(String board, String hand, int step) {
            this.board = board;
            this.hand = hand;
            this.step = step;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(board, node.board) &&
                    Objects.equals(hand, node.hand);
        }

        @Override
        public int hashCode() {
            return Objects.hash(board, hand);
        }
    }

    public int findMinStep(String board, String hand) {

        Queue<Node> queue = new LinkedList<>();
        Node head = new Node(board, hand, 0);
        Set<Node> set = new HashSet<>();
        set.add(head);
        queue.offer(head);

        while (!queue.isEmpty()){
            Node cur = queue.poll();

            for(int i = 0; i < cur.hand.length(); i++){

                char c = cur.hand.charAt(i);
                StringBuilder tempHand = new StringBuilder(cur.hand);
                tempHand.deleteCharAt(i);

                for(int j = 0; j <= cur.board.length(); j++){
                    StringBuilder tempBoard = new StringBuilder(cur.board);
                    if(j == cur.board.length()){
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

                        int cut = right - left - 1;
                        if(cut >= 3){
                            tempBoard.replace(left+1, right, "");
                            check = left;
                            if(check < 0){
                                check = 0;
                            }
                        }else {
                            break;
                        }
                    }

                    if(tempBoard.length() == 0){
                        return cur.step+1;
                    }

                    Node node = new Node(tempBoard.toString(), tempHand.toString(), cur.step+1);
                    if(!set.contains(node)){
                        set.add(node);
                        queue.offer(node);
                    }
                }
            }
        }

        return -1;
    }

    public void test(){
        System.out.println(findMinStep("RRWWRRBBRR", "WB"));
    }
}
