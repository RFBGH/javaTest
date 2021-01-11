package com.rfb.demo.rxjavatest.kotlin.collection

class CollectionTest{

    data class Person(var name:String, var age:Int)

    companion object {

        fun test(){

            val list = listOf(Person("a", 1), Person("b", 2), Person("c", 3))
            val testCollection = list
                    .map{
                        it.copy(age = 0)
                    }
                    .takeWhile {
                        it.age == 0
                    }
            println(testCollection)

        }

    }

}