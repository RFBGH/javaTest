package com.rfb.demo.rxjavatest.algorithm.leetcode4

class lcaDeepestLeaves {

     class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
     }

    val level = IntArray(1000)
    val parent = IntArray(1000){-1}

    data class Node(val i:Int, val level:Int)
    val nodes = ArrayList<Node>()
    val map = HashMap<Int, TreeNode>()
    val dp = Array(11){IntArray(1001)}

    private fun dfs(root:TreeNode, dep:Int){

        level[root.`val`] = dep
        nodes.add(Node(root.`val`, dep))
        map[root.`val`] = root

        if(root.left != null){
            parent[root.left!!.`val`] = root.`val`
            dfs(root.left!!, dep+1)
        }

        if(root.right != null){
            parent[root.right!!.`val`] = root.`val`
            dfs(root.right!!, dep+1)
        }
    }

    fun lca(s0:Int, s1:Int):Int{

        var v = s0
        var u = s1
        if(level[v] < level[u]){
            val t = v
            v = u
            u = t
        }

        while (level[v] > level[u]){
            v = parent[v]
        }

        while (v != u){
            v = parent[v]
            u = parent[u]
        }
        return v
    }
    
    fun lcaDeepestLeaves(root: TreeNode?): TreeNode? {

        if(root == null){
            return null
        }

        dfs(root, 0)
        if(nodes.size == 1){
            return root
        }

        nodes.sortWith(Comparator{
            o1, o2 ->
            when{
                o1.level < o2.level -> -1
                o1.level == o2.level -> 0
                else -> 1
            }
        })

        val n = nodes.size
        for(i in 0 until n){
            dp[0][i] = parent[i]
        }

        for(i in 1..10){
            for(j in 0 until n){
                dp[i][j] = dp[i-1][dp[i-1][j]]
            }
        }

        val node0 = nodes[nodes.size-1]
        val node1 = nodes.find {
            it.level == node0.level
        }

        if(node0 == node1){
            return map[node0.i]
        }

        return map[lca(node0.i, node1!!.i)]
    }
}