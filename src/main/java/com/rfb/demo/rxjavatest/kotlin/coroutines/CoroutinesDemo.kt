package com.rfb.demo.rxjavatest.kotlin.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.*

object CoroutinesDemo {


    fun testRepeat(){

        runBlocking {

            repeat(2){
                println("$it thread ${Thread.currentThread().name}")
                delay(1000)
            }
        }

    }

    fun blockTest():String{
        return "i am block"
    }

    private suspend fun getResult1():Int{
        println("{${Thread.currentThread().name}}")
        delay(1000)
        return 1
    }

    private suspend fun getResult2():Int{
        println("{${Thread.currentThread().name}}")
        delay(1000)
        return 2
    }

    private fun channelTest(){
        val channel = Channel<Int>()

        GlobalScope.launch(Dispatchers.IO) {
            for(i in 0..4){
                channel.send(i)
                delay(1000)
            }
            channel.close()
        }

        runBlocking {
            for(i in channel){
                println("recieve $i")
            }
        }
    }

    private fun testContext(){

        GlobalScope.launch(MyNameCoroutineContext("flypig")) {
            println(coroutineContext[MyNameCoroutineContext]?.name)
        }

    }

    private fun testIntercept(){

        GlobalScope.launch(MyIntercepter()+MyNameCoroutineContext("flypig")) {
            println("start async ${coroutineContext[MyNameCoroutineContext]?.name}")

            val result1 = async {
                getResult1()
            }.await()

            println("end async")
            println(result1)
        }


    }

    private fun testBlock(context: CoroutineContext, block: suspend () -> Unit){

        block.startCoroutine(Continuation(context) { result ->
            result.onFailure { exception ->
                val currentThread = Thread.currentThread()
                currentThread.uncaughtExceptionHandler.uncaughtException(currentThread, exception)
            }
        })

    }

    fun test0(){

        testIntercept()


//        val executor = Executors.newSingleThreadExecutor()
//
//        GlobalScope.launch(executor.asCoroutineDispatcher()){
//
//            println(Thread.currentThread().name)
//
//            val test = suspendCoroutine<String> {
//                continuation ->
//                Thread.sleep(1000)
//                continuation.resume("test")
//            }
//
//            val test1 = suspendCoroutine<String> {
//                continuation ->
//                Thread.sleep(1000)
//                continuation.resume("test")
//            }
//
//            println("next")
//
//            println(test)
//        }
//
//        GlobalScope.launch(executor.asCoroutineDispatcher()){
//
//            println(Thread.currentThread().name+" second")
//        }
//
        Thread.sleep(2000)
//        executor.shutdownNow()

    }

    fun test(){

        test0()

//        channelTest()
//
//        kotlin.run {
//            println("just run")
//        }
//
//        runBlocking (Dispatchers.IO){
//            delay(1000)
//            println("run blocking ${Thread.currentThread().name}")
//        }
//
//        println("start ${Thread.currentThread().name}")
////        testRepeat()
////        println("end")
//
//        val job = GlobalScope.launch (Dispatchers.IO, CoroutineStart.LAZY){
//
//            val result1 = GlobalScope.async {
//                getResult1()
//            }
//
//            val result2 = GlobalScope.async {
//                getResult2()
//            }
//
//            val result = result1.await() + result2.await()
//            println(result)
//        }
//
//        job.start()


    }

}