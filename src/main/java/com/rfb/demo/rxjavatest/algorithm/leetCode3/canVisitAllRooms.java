package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class canVisitAllRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {

        boolean[] gone = new boolean[rooms.size()];
        gone[0] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        while (!queue.isEmpty()){
            int cur = queue.poll();
            for(int room : rooms.get(cur)){
                if(gone[room]){
                    continue;
                }
                gone[room] = true;
                queue.offer(room);
            }
        }

        for(boolean g : gone){
            if(!g){
                return false;
            }
        }
        return true;
    }


}
