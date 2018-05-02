package com.rfb.demo.rxjavatest.tree;

import java.util.*;

/**
 * Created by Administrator on 2017/9/5 0005.
 */
public class TreeNodeHelper {

    public static final long ROOT_NODE_ID = 0L;

    private Map<Long, TreeNode> mTreeNodeMap = new HashMap<Long, TreeNode>();
    private List<ListTreeNode> mListTreeNodes = new LinkedList<ListTreeNode>();

    public TreeNodeHelper() {

        TreeNode treeNode = new TreeNode();
        treeNode.setNodeId(ROOT_NODE_ID);
        mTreeNodeMap.put(ROOT_NODE_ID, treeNode);
    }

    public void setChildren(long parentId, List<TreeNode> children){

        TreeNode parent = mTreeNodeMap.get(parentId);
        if(parent == null){
            //todo Log here
            return;
        }

        parent.setHasLoadChildren(true);
        parent.setChildren(children);
        for(TreeNode child:children){
            child.setParent(parent);
            mTreeNodeMap.put(child.getNodeId(), child);
        }
    }

    private void unexpand(int index){

        ListTreeNode listTreeNode = mListTreeNodes.get(index);
        int currLevel = listTreeNode.getLevel();

        int i = index+1;
        while(true){

            if(i >= mListTreeNodes.size()){
                break;
            }

            ListTreeNode nextListTreeNode = mListTreeNodes.get(i);
            if(nextListTreeNode.getLevel() > currLevel){
                mListTreeNodes.remove(nextListTreeNode);
                continue;
            }

            break;
        }

        listTreeNode.setExpand(false);
    }

    private ListTreeNode TreeNode2ListTreeNode(TreeNode node){
        return new ListTreeNode(false, node);
    }

    private void expand(int index){

        int insert = index+1;
        ListTreeNode listTreeNode = mListTreeNodes.get(index);
        TreeNode treeNode = mTreeNodeMap.get(listTreeNode.getNodeId());

        List<TreeNode> children = treeNode.getChildren();
        for(TreeNode child:children){
            mListTreeNodes.add(insert++, TreeNode2ListTreeNode(child));
        }

        listTreeNode.setExpand(true);
    }

    public List<ListTreeNode> expandOrUnexpand(int index){

        ListTreeNode listTreeNode = mListTreeNodes.get(index);
        if(listTreeNode == null){
            //todo Log here
            return mListTreeNodes;
        }

        boolean currExpand = listTreeNode.isExpand();
        if(currExpand){
            unexpand(index);
        }else{
            expand(index);
        }
        return mListTreeNodes;
    }

    public TreeNode findTreeNode(long id){
        return mTreeNodeMap.get(id);
    }
}
