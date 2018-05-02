package com.rfb.demo.rxjavatest.tree;

/**
 * Created by Administrator on 2017/9/5 0005.
 */
public class ListTreeNode<T> {

    private boolean mIsExpand;
    private TreeNode<T> mTreeNode;

    public ListTreeNode(boolean isExpand, TreeNode<T> treeNode) {
        mIsExpand = isExpand;
        mTreeNode = treeNode;
    }

    void setExpand(boolean isExpand) {
        mIsExpand = isExpand;
    }

    public boolean isExpand() {
        return mIsExpand;
    }

    public long getNodeId() {
        return mTreeNode.getNodeId();
    }

    public int getLevel() {
        return mTreeNode.getLevel();
    }

    public T getData() {
        return mTreeNode.getData();
    }

    public boolean isHasChildren() {
        return mTreeNode.isHasLoadChildren();
    }
}
