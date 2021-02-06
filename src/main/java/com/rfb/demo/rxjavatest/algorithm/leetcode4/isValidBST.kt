package com.rfb.demo.rxjavatest.algorithm.leetcode4

class isValidBST {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    data class Data(val max:Int, val min:Int)
    val map = HashMap<TreeNode, Data>()

    fun dfs(root:TreeNode?):Data?{

        if(root == null){
            return null
        }

        val left = dfs(root.left)
        val right = dfs(root.right)

        var min = root.`val`
        var max = root.`val`
        if(left != null){
            if(min > left.min){
                min = left.min
            }
            if(max < left.max){
                max = left.max
            }
        }

        if(right != null){
            if(min > right.min){
                min = right.min
            }
            if(max < right.max){
                max = right.max
            }
        }

        val data = Data(max, min)
        map[root] = data
        return data
    }

    fun dfs2(root: TreeNode?):Boolean{
        if(root == null){
            return true
        }

        if(!dfs2(root.left)){
            return false
        }

        if(!dfs2(root.right)){
            return false
        }

        val left = map[root.left]
        val right = map[root.right]

        if(left != null && root.`val` < left.max){
            return false
        }

        if(right != null && root.`val` > right.min){
            return false
        }

        return true
    }

    fun isValidBST(root: TreeNode?): Boolean {
        dfs(root)
        return dfs2(root)
    }
}