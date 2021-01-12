package com.rfb.demo.rxjavatest.kotlin.leetcode

import java.util.*
import kotlin.Comparator

class minRefuelStops {

    fun minRefuelStops(target: Int, startFuel: Int, stations: Array<IntArray>): Int {

        var fuel = startFuel

        Arrays.sort(stations){
            o1, o2 ->
            when {
                o1[0] < o2[0] -> -1
                o1[0] == o2[0] -> 0
                else -> 1
            }
        }

        val container = PriorityQueue<IntArray>(){
            o1, o2 ->
            when{
                o1[1] > o2[1] -> -1
                o1[1] == o2[1] -> 0
                else -> 1
            }
        }

        var ans = 0
        var cur = 0
        for(station in stations){

            fuel -= station[0] - cur
            cur = station[0]
            if(fuel < 0){
                while (fuel < 0 && !container.isEmpty()){
                    val stat = container.poll()
                    fuel += stat[1]
                    ans++
                }

                if(fuel < 0){
                    return -1
                }
            }
            container.add(station)

            if(cur == target){
                return ans
            }
        }

        fuel -= target - cur
        while (fuel < 0 && !container.isEmpty()){
            val stat = container.poll()
            fuel += stat[1]
            ans++
        }

        if(fuel < 0){
            return -1
        }

        return ans
    }

    fun test(){

        val array = Array(3){
            IntArray(2)
        }

        array[0][0] = 25
        array[0][1] = 25

        array[1][0] = 50
        array[1][1] = 25

        array[2][0] = 75
        array[2][1] = 25

        println(minRefuelStops(100, 25, array))
    }
}