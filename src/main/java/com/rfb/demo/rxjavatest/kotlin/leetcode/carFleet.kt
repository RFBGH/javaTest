package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.Comparator

class carFleet {

    data class Node(val pos:Int, val speed:Int)

    fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {

        val nodes = Array(position.size){
            Node(position[it], speed[it])
        }

        nodes.sortWith(Comparator { o1, o2 ->
            when{
                o1.pos < o2.pos -> -1
                o1.pos > o2.pos -> 1
                else -> 0
            }
        })

        val stack = Stack<Int>()
        for(i in nodes.indices){
            if(stack.isEmpty()){
                stack.push(i)
            }else{

                while (!stack.isEmpty()){
                    val top = stack.pop()
                    if(nodes[top].speed <= nodes[i].speed){
                        stack.push(top)
                        break
                    }

                    val togetherPos = nodes[top].pos + nodes[top].speed*(nodes[i].pos-nodes[top].pos).toFloat()/(nodes[top].speed-nodes[i].speed)
                    if(togetherPos > target){
                        stack.push(top)
                        break
                    }
                }
                stack.push(i)
            }
        }

        return stack.size
    }

    fun test(){
        println(carFleet(13, intArrayOf(10,2,5,7,4,6,11), intArrayOf(7,5,10,5,9,4,1)))
    }
}