package com.rfb.demo.rxjavatest.algorithm;

import java.util.Stack;

/**
 * Created by Administrator on 2020/6/23 0023.
 */
public class BinaryTree{

    private int mValue;
    private BinaryTree mLeftSon;
    private BinaryTree mRightSon;

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public BinaryTree getLeftSon() {
        return mLeftSon;
    }

    public void setLeftSon(BinaryTree leftSon) {
        mLeftSon = leftSon;
    }

    public BinaryTree getRightSon() {
        return mRightSon;
    }

    public void setRightSon(BinaryTree rightSon) {
        mRightSon = rightSon;
    }

    public static void preorder(BinaryTree root){

        if(root == null){
            return;
        }

        System.out.println(root.getValue());
        preorder(root.getLeftSon());
        preorder(root.getRightSon());
    }

    public static void preorder2(BinaryTree root){

        if(root == null){
            return;
        }

        Stack<BinaryTree> stack = new Stack<>();

        BinaryTree cur = root;
        while(cur != null){


            while (cur != null){
                System.out.println(cur.getValue());
                stack.push(cur);
                cur = cur.getLeftSon();
            }

            cur = stack.pop();
            cur = cur.getRightSon();
        }
    }

    public static void leaf(BinaryTree root){

    }

    public static void middle(BinaryTree root){

        if(root == null){
            return;
        }

        preorder(root.getLeftSon());
        System.out.println(root.getValue());
        preorder(root.getRightSon());
    }

    public static void after(BinaryTree root){

        if(root == null){
            return;
        }

        preorder(root.getLeftSon());
        preorder(root.getRightSon());
        System.out.println(root.getValue());
    }
}
