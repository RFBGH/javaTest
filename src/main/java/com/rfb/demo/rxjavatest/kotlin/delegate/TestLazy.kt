package com.rfb.demo.rxjavatest.kotlin.delegate

class TestLazy {
    val a by lazy{
        println("lazy Init")
        "xxxxxx"
    }
}