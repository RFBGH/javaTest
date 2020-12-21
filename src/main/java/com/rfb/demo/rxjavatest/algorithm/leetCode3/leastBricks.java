package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class leastBricks {

    public int leastBricks(List<List<Integer>> wall) {

        Map<Integer, Integer> map = new HashMap<>();

        for(List<Integer> row : wall){
            int sum = 0;
            for(Integer gap : row){
                sum += gap;
                Integer count = map.get(sum);
                if(count == null){
                    count = 1;
                }else{
                    count++;
                }
                map.put(sum, count);
            }
            map.remove(sum);
        }

        int max = 0;
        for(Integer key : map.keySet()){
            int count = map.get(key);
            if(max < count){
                max = count;
            }
        }

        return wall.size()-max;
    }

    public void test(){

        List<List<Integer>> wall = new ArrayList<>();
        wall.add(new ArrayList<>(Arrays.asList(1,2,2,1)));
        wall.add(new ArrayList<>(Arrays.asList(3,1,2)));
        wall.add(new ArrayList<>(Arrays.asList(1,3,2)));
        wall.add(new ArrayList<>(Arrays.asList(2,4)));
        wall.add(new ArrayList<>(Arrays.asList(3,1,2)));
        wall.add(new ArrayList<>(Arrays.asList(1,3,1,1)));

        System.out.println(leastBricks(wall));

    }



}
