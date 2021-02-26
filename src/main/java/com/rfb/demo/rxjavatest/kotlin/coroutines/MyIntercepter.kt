package com.rfb.demo.rxjavatest.kotlin.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext.Key

class MyIntercepter :ContinuationInterceptor{

    override val key: Key<MyIntercepter> get(){
        return object:Key<MyIntercepter> {}
    }

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
        return MyIntercepterContinuation(continuation)
    }
}