package com.rfb.demo.rxjavatest.algorithm.leetCode2;

public class canCompleteCircuit {

    public int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length;

        if(n == 1){
            return gas[0] >= cost[0] ? 0:-1;
        }

        for(int i = 0; i < n; i++){

            int cur = gas[i];
            cur -= cost[i];
            if(cur < 0){
                continue;
            }

            int j;
            for(j = i+1==n?0:i+1; j != i; j = j+1==n?0:j+1){
                cur += gas[j];
                cur -= cost[j];
                if(cur < 0){
                    break;
                }
            }

            if(j == i){
                return i;
            }
        }

        return -1;
    }

    public void test(){
        System.out.println(canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
        System.out.println(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }
}
