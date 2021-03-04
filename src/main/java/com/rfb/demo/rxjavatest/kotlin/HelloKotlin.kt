package com.rfb.demo.rxjavatest.kotlin

import com.rfb.demo.rxjavatest.kotlin.leetcode.*

class C{
    val c = 1
}
class B{
    var c:C? = null
}

class A{
    var b:B? = null
}
class HelloKotlin {



    companion object{
        fun test(){

            val param = Param()
            param.func = {
                println("doFunc")
            }

            param.func?.invoke()

            param.func2 {
                a = 2
            }
//            val a = A()
//            println(a.b?.c?.c)

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

//            val test = accountsMerge()
//            test.test()

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