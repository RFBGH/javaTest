package com.rfb.demo.rxjavatest.kotlin.leetcode

class StreamChecker(words: Array<String>) {

    private class Tire(val isWord:Boolean){
        val son = Array<Tire?>(26){null}
    }

    private var root = Tire(false)
    private var curs = HashSet<Tire>()

    private fun addWord(word:String){
        var cur = root
        for(i in word.indices){
            val c = word[i] - 'a'

            if(cur.son[c] == null){
                cur.son[c] = Tire(i == word.length-1)
            }
            cur = cur.son[c]!!
        }
    }

    init {
        words.forEach {
            addWord(it)
        }
    }

    fun query(letter: Char): Boolean {

        curs.add(root)
        var result = false
        val index = letter - 'a'
        val add = HashSet<Tire>()
        for(cur in curs){
            if(cur.son[index] != null){
                add.add(cur.son[index]!!)
                if(cur.son[index]!!.isWord){
                    result = true
                }
            }
        }

        curs = add
        return result
    }
}