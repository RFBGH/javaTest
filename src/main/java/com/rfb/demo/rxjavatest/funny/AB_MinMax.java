package com.rfb.demo.rxjavatest.funny;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/10 0010.
 */
public class AB_MinMax {

    public static final int TYPE_MIN = 1;
    public static final int TYPE_MAX = 0;


    public static class TreeNode{

        public int type;
        public int bound;

        public TreeNode parent;
        public List<TreeNode> sons;
    }

    public static class Tree{

        public Tree parent;
        public List<Tree> sons;
        public int value;
        public int type;

        public Tree(Tree parent) {
            this.parent = parent;
            sons = new ArrayList<Tree>();
            if(type == TYPE_MIN){
                value = Integer.MAX_VALUE;
            }
            if(parent != null){
                parent.sons.add(this);
            }
        }

        public Tree(Tree parent, int value) {
            this.parent = parent;
            sons = new ArrayList<Tree>();
            if(parent != null){
                parent.sons.add(this);
            }
            this.value = value;
        }
    }

    public void deal(){

        Tree root = new Tree(null, Integer.MIN_VALUE);

        Tree son1 = new Tree(root, Integer.MAX_VALUE);
        Tree son2 = new Tree(root, Integer.MAX_VALUE);
        Tree son3 = new Tree(root, Integer.MAX_VALUE);

        Tree son11 = new Tree(son1, Integer.MIN_VALUE);
        Tree son12 = new Tree(son1, Integer.MIN_VALUE);

        Tree son21 = new Tree(son2, Integer.MIN_VALUE);
        Tree son22 = new Tree(son2, Integer.MIN_VALUE);

        Tree son31 = new Tree(son3, Integer.MIN_VALUE);
        Tree son32 = new Tree(son3, Integer.MIN_VALUE);

        Tree son111 = new Tree(son11, Integer.MAX_VALUE);
        Tree son112 = new Tree(son11, Integer.MAX_VALUE);

        Tree son121 = new Tree(son12, Integer.MAX_VALUE);

        Tree son211 = new Tree(son21, Integer.MAX_VALUE);
        Tree son212 = new Tree(son21, Integer.MAX_VALUE);

        Tree son221 = new Tree(son22, Integer.MAX_VALUE);

        Tree son311 = new Tree(son31, Integer.MAX_VALUE);

        Tree son321 = new Tree(son32, Integer.MAX_VALUE);
        Tree son322 = new Tree(son32, Integer.MAX_VALUE);

        Tree son1111 = new Tree(son111, 5);
        Tree son1112 = new Tree(son111, 6);

        Tree son1121 = new Tree(son112, 7);
        Tree son1122 = new Tree(son112, 4);
        Tree son1123 = new Tree(son112, 5);

        Tree son1211 = new Tree(son121, 3);

        Tree son2111 = new Tree(son211, 6);

        Tree son2121 = new Tree(son212, 6);
        Tree son2122 = new Tree(son212, 9);

        Tree son2211 = new Tree(son221, 7);

        Tree son3111 = new Tree(son311, 5);

        Tree son3211 = new Tree(son321, 9);
        Tree son3212 = new Tree(son321, 8);

        Tree son3221 = new Tree(son322, 6);

        int max = Integer.MIN_VALUE;
        for(Tree son:root.sons){

            int value = dfsTree(son, TYPE_MIN);
            if(max < value){
                max = value;
            }
        }
        root.value = max;
    }

    public int dfsTree(Tree cur, int type){

        if(cur.sons.isEmpty()){
            return cur.value;
        }

        Tree parent = cur.parent;

        if(type == TYPE_MAX){

            int max = Integer.MIN_VALUE;
            for(Tree son:cur.sons){

                int bound = dfsTree(son, TYPE_MIN);
                if(max < bound){
                    max = bound;
                }

                if(max >= parent.value){
                    break;
                }
            }

            cur.value = max;
            if(parent.value > max){
                parent.value = max;
            }

            return max;

        }else{

            int min = Integer.MAX_VALUE;
            for(Tree son:cur.sons){

                int bound = dfsTree(son, TYPE_MAX);
                if(min > bound){
                    min = bound;
                }

                if(min <= parent.value){
                    break;
                }
            }

            cur.value = min;
            if(parent.value < min){
                parent.value = min;
            }

            return min;
        }
    }

    public int dfs(TreeNode cur){

        if(cur.sons.isEmpty()){
            return cur.bound;
        }

        TreeNode parent = cur.parent;

        if(cur.type == TYPE_MAX){

            int max = Integer.MIN_VALUE;
            for(TreeNode son:cur.sons){

                int bound = dfs(son);
                if(max < bound){
                    max = bound;
                }

                if(max >= parent.bound){
                    break;
                }
            }

            cur.bound = max;
            if(parent.bound > max){
                parent.bound = max;
            }

            return max;
        }else{

            int min = Integer.MAX_VALUE;
            for(TreeNode son:cur.sons){

                int bound = dfs(son);
                if(min > bound){
                    min = bound;
                }

                if(min <= parent.bound){
                    break;
                }
            }

            cur.bound = min;
            if(parent.bound < min){
                parent.bound = min;
            }

            return min;
        }
    }
}

