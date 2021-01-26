package com.rfb.demo.rxjavatest.algorithm.leetcode4

class insertIntoBST {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun insertIntoBST(root: TreeNode?, v: Int): TreeNode? {

        var cur = root
        var parent:TreeNode? = null
        while (cur != null){

            parent = cur
            if(cur.`val` > v){
                cur = cur.left
            }else{
                cur = cur.right
            }
        }

        if(parent == null){
            return TreeNode(v)
        }

        if(parent.`val` > v){
            parent.left = TreeNode(v)
        }else{
            parent.right = TreeNode(v)
        }

        return root
    }
}