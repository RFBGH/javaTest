package com.rfb.demo.rxjavatest.funny;

import java.util.Scanner;

/**
 * Created by Administrator on 2018/2/24 0024.
 */
public class MinMax {

    int N = 100;

    public void test(){


        Node root = createTree(1);
        markTree(root, 0);
        play(root);
    }

    public void play(Node root){

        int score = 1;
        int role = 0;
        Node curSelNode = root;
        while(true){
            int pick = 2;
            if(role % 2 == 0){
                if(curSelNode.win){

                    for(int i = 0; i < 8; i++){
                        Node son = curSelNode.sonNode[i];
                        if(son.win){
                            pick = i+2;
                            break;
                        }
                    }
                }
            }else{
                Scanner input=new Scanner(System.in);
                pick = input.nextInt();
            }

            score *= pick;
            System.out.println(((role%2 == 0) ? "compute":"I")+" pick "+pick+" now score "+score);
            if(score >= N){
                System.out.println(((role%2 == 0) ? "compute":"I")+" win");
                break;
            }

            role = (role + 1) % 2;
            curSelNode = curSelNode.sonNode[pick-2];

        }
    }

    public void markTree(Node node, int role){

        if(node.sonNode[0] == null){

            if(role % 2 == 1){
                node.win = true;
            }else{
                node.win = false;
            }
            return;
        }

        for(Node son:node.sonNode){
            markTree(son, (role+1)%2);
        }

        if(role % 2 == 0){

            boolean win = false;
            for(Node son:node.sonNode){
                if(son.win){
                    win = true;
                    break;
                }
            }
            node.win = win;
        }else{

            boolean win = true;
            for(Node son:node.sonNode){
                if(!son.win){
                    win = false;
                    break;
                }
            }
            node.win = win;
        }

    }

    public Node createTree(int curScore){

        Node node = new Node();
        if(curScore >= N){
            return node;
        }

        for(int i = 2; i <= 9; i++){
            int nextScore = curScore*i;
            node.sonNode[i-2] = createTree(nextScore);
        }
        return node;
    }

    public static class Node{

        public boolean win;
        public Node sonNode[] = new Node[8];
    }

}
