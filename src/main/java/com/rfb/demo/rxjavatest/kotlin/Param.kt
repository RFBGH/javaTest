package com.rfb.demo.rxjavatest.kotlin


class Param {

    var a = 0

    var func:(()->Unit)? = null
        get() {
            return {
                println("start")
                field?.invoke()
            }
        }

    fun func2(block:Param.()->Unit){
        block.invoke(this)
    }
}