package com.rfb.demo.rxjavatest.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/20 0020.
 */
public class MaxDividerMatch{

    private List<List<Integer>> G = new ArrayList<>();
    private List<Boolean> used = new ArrayList<>();
    private List<Integer>match = new ArrayList<>();

    public void init(int n){
        for(int i = 0; i < n; i++){
            List<Integer> nodes = new ArrayList<>();
            G.add(nodes);
            used.add(false);
            match.add(-1);
        }
    }

    private void resetUsed(){
        int n = used.size();
        used.clear();
        while(n > 0){
            used.add(false);
            n--;
        }
    }

    public void add(int from, int to){
        G.get(from).add(to);
    }

    public boolean dfs(int u){

        List<Integer> edge = G.get(u);
        for(Integer v:edge){

            if(used.get(v)){
                continue;
            }

            used.set(v, true);
            if(match.get(v) == -1 || dfs(match.get(v))){
                match.set(v, u);
                return true;
            }
        }

        return false;
    }

    public int calcMatch(){

        int ans = 0;
        for(int i = 0, size = G.size(); i < size; i++){
            resetUsed();
            if(dfs(i)){
                ans ++;
            }
        }
        return ans;
    }

    public static void test(){

    }
}
