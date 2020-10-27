package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class FlattenTree {


    public static class BinaryTree{
        public int data;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int data) {
            this.data = data;
        }
    }

    public static void flatten(BinaryTree root){

        if(root == null){
            return;
        }

        flatten(root.left);
        flatten(root.right);

        BinaryTree findSon = root.left;
        if(findSon != null){

            while (findSon.right != null){
                findSon = findSon.right;
            }

            findSon.right = root.right;

            root.right = root.left;
            root.left = null;
        }
    }

    public static void test(){

        BinaryTree root = new BinaryTree(1);
        BinaryTree left = new BinaryTree(2);
        BinaryTree right = new BinaryTree(5);
        root.left = left;
        root.right = right;

        BinaryTree leftLeft = new BinaryTree(3);
        BinaryTree leftRight = new BinaryTree(4);
        left.left = leftLeft;
        left.right = leftRight;

        BinaryTree rightRight = new BinaryTree(6);
        right.right = rightRight;

        flatten(root);

        System.out.println(root);
    }
}
