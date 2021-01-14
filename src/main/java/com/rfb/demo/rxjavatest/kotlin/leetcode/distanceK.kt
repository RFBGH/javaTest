package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.collections.ArrayList

class distanceK {

    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    data class Node(val i:Int, val step:Int, val prev:Node?)

    private lateinit var G:Array<ArrayList<Int>>

    private fun dfs(root:TreeNode){

        if(root.left != null){
            G[root.`val`].add(root.left!!.`val`)
            G[root.left!!.`val`].add(root.`val`)
            dfs(root.left!!)
        }

        if(root.right != null){
            G[root.`val`].add(root.right!!.`val`)
            G[root.right!!.`val`].add(root.`val`)
            dfs(root.right!!)
        }
    }

    fun distanceK(root: TreeNode?, target: TreeNode?, K: Int): List<Int> {

        val ans = ArrayList<Int>()
        if(root == null){
            return ans
        }

        G = Array(501){
            ArrayList<Int>()
        }

        dfs(root)

        val queue:Queue<Node> = LinkedList<Node>()
        queue.offer(Node(target!!.`val`, 0, null))

        while (!queue.isEmpty()){
            val cur = queue.poll()

            if(cur.step == K){
                ans.add(cur.i)
                continue
            }

            for(next in G[cur.i]){

                if(cur.prev != null && cur.prev.i == next){
                    continue
                }

                queue.offer(Node(next, cur.step+1, cur))
            }
        }

        return ans
    }

}