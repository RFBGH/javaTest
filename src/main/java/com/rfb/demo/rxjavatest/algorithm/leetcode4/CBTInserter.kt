package com.rfb.demo.rxjavatest.algorithm.leetcode4

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}


class CBTInserter(root: TreeNode?) {


    var root = root!!

    private fun insertParentRoot():TreeNode{

        var levelCount = 2
        var parent = ArrayList<TreeNode>()
        var sons = ArrayList<TreeNode>()
        parent.add(root)
        while (true){

            parent.forEach {
                if(it.left != null){
                    sons.add(it.left!!)
                }

                if(it.right != null){
                    sons.add(it.right!!)
                }
            }

            if(sons.size < levelCount){
                break
            }
            levelCount *= 2
            parent = sons
            sons = ArrayList()
        }

        if(sons.isEmpty()){
            return parent[0]
        }

        var parentIndex = (sons.size-1) / 2
        if((sons.size-1) % 2 == 1){
            parentIndex++
        }

        return parent[parentIndex]
    }

    fun insert(v: Int): Int {

        val parent = insertParentRoot()
        if(parent.left == null){
            parent.left = TreeNode(v)
        }else{
            parent.right = TreeNode(v)
        }
        return parent.`val`
    }

    fun get_root(): TreeNode? {
        return root
    }

}