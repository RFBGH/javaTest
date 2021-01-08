package com.rfb.demo.rxjavatest.kotlin

import com.rfb.demo.rxjavatest.kotlin.collection.CollectionTest
import com.rfb.demo.rxjavatest.kotlin.delegate.Person
import com.rfb.demo.rxjavatest.kotlin.delegate.TestLazy

class HelloKotlin {

    companion object{
        fun test(){

            println("Hello Kotlin")

            val person = Person("xx","yyy")
            person.name = "zzz"

            println(person)

            val lzy = TestLazy()
            println(lzy.a)
            println(lzy.a)

            CollectionTest.test()
        }
    }

}