package com.rfb.demo.rxjavatest.kotlin.delegate

class Person(name:String, lastname:String) {

    var updateCount = 0

    var name by FormatDelegate()
    var lastname by FormatDelegate()

    override fun toString(): String {
        return "name:$name lastname:$lastname updateCount:$updateCount"
    }
}