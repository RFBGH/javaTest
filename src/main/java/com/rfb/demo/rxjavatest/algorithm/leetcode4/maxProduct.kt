package com.rfb.demo.rxjavatest.algorithm.leetcode4

class maxProduct {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    val sums = ArrayList<Int>()

    fun dfs(root:TreeNode?):Int{

        if(root == null){
            return 0
        }

        val value = dfs(root.left) + dfs(root.right) + root.`val`
        sums.add(value)
        return value
    }

    fun maxProduct(root: TreeNode?): Int {

        if(root == null){
            return 0
        }

        val sum = dfs(root)
        var max:Long = 0
        sums.forEach {
            val temp:Long = (it.toLong()) * (sum - it).toLong()
            if(max < temp){
                max = temp
            }
        }

        return max.toInt()
    }

}