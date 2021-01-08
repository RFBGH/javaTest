package com.rfb.demo.rxjavatest.kotlin.seal

sealed class Result
class Success(val msg:String) : Result()
sealed class Faild(val t:Throwable) : Result()
class SystemFaild(t:Throwable) : Faild(t)
class CustomFaild(t:Throwable) : Faild(t)

fun getMessage(result:Result) = when(result){
    is Success -> println(result.msg)
    is SystemFaild -> result.t.printStackTrace()
    is CustomFaild -> result.t.printStackTrace()
}

