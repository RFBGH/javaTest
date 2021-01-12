package com.rfb.demo.rxjavatest.kotlin

import com.rfb.demo.rxjavatest.kotlin.collection.CollectionTest
import com.rfb.demo.rxjavatest.kotlin.delegate.Person
import com.rfb.demo.rxjavatest.kotlin.delegate.TestLazy
import com.rfb.demo.rxjavatest.kotlin.delegate.numSimilarGroups
import com.rfb.demo.rxjavatest.kotlin.delegate.stoneGame
import com.rfb.demo.rxjavatest.kotlin.leetcode.minRefuelStops
import com.rfb.demo.rxjavatest.kotlin.leetcode.smallestStringWithSwaps
import java.util.ArrayList

class HelloKotlin {

    companion object{
        fun test(){

//            println("Hello Kotlin")
//
//            val person = Person("xx","yyy")
//            person.name = "zzz"
//
//            println(person)
//
//            val lzy = TestLazy()
//            println(lzy.a)
//            println(lzy.a)
//
//            CollectionTest.test()

            val test = stoneGame()
            test.test()

//            val site = object{
//                var name = "123"
//                var url = "2345"
//            }
//
//            val sumLambda : (Int, Int)->Int = {x, y -> x + y}
//
//            println(sumLambda(1, 2))

//            val list = ArrayList<Int>()
//            list.addAll(Array(4) { it })
//
//            list.forEach {
//                println(it)
//            }
//
//            println(list.indexOfFirst {
//                it > 5
//            })

        }
    }

}