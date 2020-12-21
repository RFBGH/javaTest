package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class smallestRange2 {

    public int[] smallestRange(List<List<Integer>> nums) {

        List<Integer> list = new ArrayList<>();
        Map<Integer, Set<Integer>> belongMap = new HashMap<>();
        for(int i = 0; i < nums.size(); i++){

            List<Integer> num = nums.get(i);
            list.addAll(num);
            for(int j = 0; j < num.size(); j++){
                Set<Integer> set = belongMap.get(num.get(j));
                if(set == null){
                    set = new HashSet<>();
                    belongMap.put(num.get(j), set);
                }
                set.add(i);
            }
        }

        Collections.sort(list);

        int from = list.get(0);
        int to = list.get(list.size()-1);

        int[] sums = new int[nums.size()];
        int left = 0;
        for(int i = 0; i < list.size(); i++){

            Set<Integer> belongs = belongMap.get(list.get(i));
            for(Integer group : belongs){
                sums[group]++;
            }

            while (true){

                int take = 0;
                for(int sum : sums){
                    if(sum == 0){
                        break;
                    }
                    take++;
                }

                if(take == sums.length){
                    if(to - from > list.get(i) - list.get(left)){
                        to = list.get(i);
                        from = list.get(left);
                    }
                }else{
                    break;
                }

                belongs = belongMap.get(list.get(left++));
                for(Integer group : belongs){
                    sums[group]--;
                }
            }
        }

        return new int[]{from, to};
    }

    public void test(){
        List<List<Integer>>lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{4,10,15,24,26}));
        lists.add(list);
        list = new ArrayList<>(Arrays.asList(new Integer[]{0,9,12,20}));
        lists.add(list);
        list = new ArrayList<>(Arrays.asList(new Integer[]{5,18,22,30}));
        lists.add(list);

        smallestRange(lists);
    }
}
