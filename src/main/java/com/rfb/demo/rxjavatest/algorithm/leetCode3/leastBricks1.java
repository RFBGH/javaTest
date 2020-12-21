package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class leastBricks1 {

    private static class Node{
        int from;
        int to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public int leastBricks(List<List<Integer>> wall) {

        Set<Integer> gaps = new HashSet<>();

        List<Node>[] WALL = new ArrayList[wall.size()];
        for(int i = 0; i < wall.size(); i++){
            WALL[i] = new ArrayList<>();
        }

        for(int i = 0; i < wall.size(); i++){
            List<Integer> row = wall.get(i);

            int offset = 0;
            for(Integer len : row){
                WALL[i].add(new Node(offset, offset+len));
                offset += len;
                gaps.add(offset);
            }
            gaps.remove(offset);
        }

        if(gaps.isEmpty()){
            return wall.size();
        }

        int min = Integer.MAX_VALUE;
        for(Integer gap : gaps){

            int count = 0;
            for(int i = 0; i < WALL.length; i++){

                int left = 0;
                int right = WALL[i].size()-1;
                int mid;
                while (true){

                    mid = (left + right) / 2;
                    if(WALL[i].get(mid).to < gap){
                        left = mid+1;
                    }else if(WALL[i].get(mid).from > gap){
                        right = mid;
                    }else{
                        break;
                    }
                }

                if(WALL[i].get(mid).from < gap && gap < WALL[i].get(mid).to){
                    count++;
                }
            }

            if(count < min){
                min = count;
            }
        }

        return min;
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
