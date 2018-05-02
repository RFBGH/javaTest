package com.rfb.demo.rxjavatest.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28 0028.
 */
public class TreeNode<T>{

    private long nodeId;
    private T mData;
    private TreeNode mParent;
    private List<TreeNode> mChildren = new ArrayList<TreeNode>();
    private boolean mHasLoadChildren;

    public TreeNode() {

        mHasLoadChildren = false;
    }

    public boolean isHasLoadChildren() {
        return mHasLoadChildren;
    }

    public void setHasLoadChildren(boolean hasLoadChildren) {
        mHasLoadChildren = hasLoadChildren;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public TreeNode getParent(){
        return mParent;
    }

    public boolean isLeaf(){
        return mChildren.size() == 0;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public int getLevel() {
        if(mParent == null){
            return 0;
        }
        return mParent.getLevel()+1;
    }

    public void setParent(TreeNode parent) {
        mParent = parent;
    }

    public void appendChildren(List<TreeNode> sons) {
        mChildren.addAll(sons);
    }

    public void setChildren(List<TreeNode> children) {
        mChildren.clear();
        mChildren.addAll(children);
    }

    public List<TreeNode> getChildren(){
        return mChildren;
    }
}

