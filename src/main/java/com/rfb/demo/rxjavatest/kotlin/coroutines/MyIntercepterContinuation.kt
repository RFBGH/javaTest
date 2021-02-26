package com.rfb.demo.rxjavatest.kotlin.coroutines

import kotlin.coroutines.Continuation

class MyIntercepterContinuation<T>(val cont:Continuation<T>):Continuation<T>{
    override val context = cont.context

    override fun resumeWith(result: Result<T>) {
        println("myIntercept")
        cont.resumeWith(result)
    }
}