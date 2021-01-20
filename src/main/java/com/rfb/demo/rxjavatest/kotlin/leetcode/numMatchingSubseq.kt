package com.rfb.demo.rxjavatest.kotlin.leetcode

class numMatchingSubseq {

    private data class Node(val s:String, val index:Int)

    fun numMatchingSubseq(S: String, words: Array<String>): Int {

        val nodes = Array(26){ArrayList<Node>()}

        words.forEach {
            nodes[it[0]-'a'].add(Node(it, 0))
        }

        var ans = 0

        S.forEach {

            val cur = ArrayList<Node>(nodes[it-'a'])
            nodes[it-'a'].clear()

            cur.forEach {
                node ->
                var index = node.index+1
                if(index < node.s.length){
                    nodes[node.s[index]-'a'].add(Node(node.s, index))
                }else{
                    ans++
                }
            }
        }

        return ans
    }
}