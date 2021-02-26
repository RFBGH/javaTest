package com.rfb.demo.rxjavatest.kotlin.coroutines

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext


class MyNameCoroutineContext(val name:String) : AbstractCoroutineContextElement(MyNameCoroutineContext) {
    companion object Key: CoroutineContext.Key<MyNameCoroutineContext>
}