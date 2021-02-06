package com.rfb.demo.rxjavatest.algorithm.leetcode4

class cloneGraph{

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList<Node?>()
    }

    val map = HashMap<Int, Node>()

    fun dfs(root:Node?):Node?{

        if(root == null){
            return null
        }

        if(map.containsKey(root.`val`)){
            return map[root.`val`]
        }

        val copy = Node(root.`val`)
        map[copy.`val`] = copy
        root.neighbors.forEach {
            copy.neighbors.add(dfs(it))
        }

        return copy
    }

    fun cloneGraph(node: Node?): Node? {
        return dfs(node)
    }
}