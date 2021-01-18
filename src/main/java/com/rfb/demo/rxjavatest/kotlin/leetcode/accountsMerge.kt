package com.rfb.demo.rxjavatest.kotlin.leetcode

class accountsMerge{


    private lateinit var parents:IntArray

    private fun getLevelAndParent(u:Int):Pair<Int, Int>{

        var cur = u
        var level = 0
        while (parents[cur] != -1){
            cur = parents[cur]
            level++
        }
        return Pair(level, cur)
    }

    private fun union(v:Int, u:Int):Boolean{

        val pairV = getLevelAndParent(v)
        val pairU = getLevelAndParent(u)

        if (pairU.second == pairV.second) {
            return false
        }

        if(pairV.first> pairU.first){
            parents[pairU.second] = pairV.second
        }else{
            parents[pairV.second] = pairU.second
        }
        return true
    }

    data class AccountInfo(val names:ArrayList<String>, val accounts:ArrayList<String>)

    fun accountsMerge(accounts: List<List<String>>): List<List<String>> {

        parents = IntArray(accounts.size){-1}

        var count = 0
        for(i in accounts.indices){
            val sets = HashSet<String>(accounts[i])
            for(j in i+1 until accounts.size){
                for(k in 1 until accounts[j].size){
                    if(sets.contains(accounts[j][k])){
                        if(union(i, j)){
                            count++
                        }
                        break
                    }
                }

                if(count == accounts.size){
                    break
                }
            }

            if(count == accounts.size){
                break
            }
        }

        val ans = Array(accounts.size){AccountInfo(ArrayList(), ArrayList())}

        for(i in accounts.indices){
            val pair = getLevelAndParent(i)
            if(i < pair.second){
                ans[pair.second].names.add(0, accounts[i][0])

                for(j in 1 until accounts[i].size){
                    if (ans[pair.second].accounts.contains(accounts[i][j])) {
                        continue
                    }
                    ans[pair.second].accounts.add(0, accounts[i][j])
                }
            }else{
                ans[pair.second].names.add(accounts[i][0])
                for(j in 1 until accounts[i].size){
                    if (ans[pair.second].accounts.contains(accounts[i][j])) {
                        continue
                    }
                    ans[pair.second].accounts.add(accounts[i][j])
                }
            }
        }

        val result = ArrayList<ArrayList<String>>()

        for(i in ans.indices){
            if(ans[i].names.isEmpty()){
                continue
            }

            val list = ArrayList<String>()
            list.add(ans[i].names[0])
            ans[i].accounts.sort()
            list.addAll(ans[i].accounts)
            result.add(list)
        }

        return result
    }

    fun test(){

        val list1 = listOf("John","johnsmith@mail.com","john_newyork@mail.com")
        val list2 = listOf("John","johnsmith@mail.com","john00@mail.com")
        val list3 = listOf("Mary","mary@mail.com")
        val list4 = listOf("John","johnnybravo@mail.com")
        val list = listOf(list1, list2, list3, list4)
        accountsMerge(list)
    }
}