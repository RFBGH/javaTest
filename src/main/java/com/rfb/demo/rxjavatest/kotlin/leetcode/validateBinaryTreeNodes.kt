package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*

class validateBinaryTreeNodes {

    fun validateBinaryTreeNodes(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean {

        val dep = IntArray(n) { 0 }

        val depCountAndCheck: (Int) -> Boolean = { it ->
            if (it != -1) {
                dep[it]++
                dep[it] > 1
            } else {
                false
            }
        }

        leftChild.forEachIndexed { index, i ->

            if (i == index) {
                return false
            }
            if (depCountAndCheck(i)) {
                return false
            }
        }

        rightChild.forEachIndexed { index, i ->

            if (i == index) {
                return false
            }
            if (depCountAndCheck(i)) {
                return false
            }
        }

        val queue = LinkedList<Int>()
        dep.forEachIndexed { index, i ->
            if(i == 0){
                queue.offer(index)
            }
        }

        if (queue.size > 1) {
            return false
        }

        var count = 0
        while (!queue.isEmpty()){
            val cur = queue.poll()
            count++

            val left = leftChild[cur]
            if(left != -1){
                dep[left]--
                if(dep[left] == 0){
                    queue.offer(left)
                }
            }

            val right = rightChild[cur]
            if(right != -1){
                dep[right]--
                if(dep[right] == 0){
                    queue.offer(right)
                }
            }
        }

        return count == n
    }
}