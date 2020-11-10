package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class sortItems {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

        List<Integer>[] taskG = new ArrayList[n];
        for(int i = 0; i < n; i++){
            taskG[i] = new ArrayList<>();
        }

        List<Integer>[] groupG = new ArrayList[m];
        for(int i = 0; i < m; i++){
            groupG[i] = new ArrayList<>();
        }

        List<Integer>[] groupBefores = new ArrayList[m];
        for(int i = 0; i < m; i++){
            groupBefores[i] = new ArrayList<>();
        }

        List<Integer>[] groupHasTasks = new ArrayList[m];
        List<Integer> unbelong = new ArrayList<>();

        for(int i = 0; i < m; i++){
            groupHasTasks[i] = new ArrayList<>();
        }

        int[] taskDep = new int[n];
        int[] groupDep = new int[m];

        for(int i = 0; i < n; i++){
            List<Integer> list = beforeItems.get(i);
            taskDep[i] = list.size();

            List<Integer> groupBefore = null;
            if(group[i] != -1){
                groupBefore = groupBefores[group[i]];
                groupHasTasks[group[i]].add(i);
            }else{
                unbelong.add(i);
            }

            for(Integer p : list){
                taskG[p].add(i);
                int g = group[p];
                if(groupBefore != null
                        && g != -1
                        && !groupBefore.contains(g)
                        && g != group[i]){
                        groupBefore.add(g);
                }
            }
        }

        Queue<Integer> groupQueue = new LinkedList<>();
        for(int i = 0; i < m; i++){
            groupDep[i] = groupBefores[i].size();
            for(int g : groupBefores[i]){
                groupG[g].add(i);
            }
            if(groupDep[i] == 0){
                groupQueue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!groupQueue.isEmpty()){
            int curGroup = groupQueue.poll();

            Queue<Integer> taskQueue = new LinkedList<>();
            for(int i : unbelong){
                if(taskDep[i] == 0 && !result.contains(i)){
                    result.add(i);
                    taskQueue.offer(i);
                }
            }

            int count = 0;
            for(int i : groupHasTasks[curGroup]){
                if(taskDep[i] == 0){
                    result.add(i);
                    taskQueue.offer(i);
                    count++;
                }
            }

            while (!taskQueue.isEmpty()){
                int curTask = taskQueue.poll();
                for(int next : taskG[curTask]){
                    taskDep[next]--;
                    if(taskDep[next] == 0 && group[next] == curGroup){
                        result.add(next);
                        taskQueue.offer(next);
                        count++;
                    }
                }
            }

            if(count == groupHasTasks[curGroup].size()){
                for(int next : groupG[curGroup]){
                    groupDep[next]--;
                    if(groupDep[next] == 0){
                        groupQueue.offer(next);
                    }
                }
            }else{
                break;
            }
        }

        if(!unbelong.isEmpty()){
            for(int i : unbelong){
                if(taskDep[i] == 0 && !result.contains(i)){
                    result.add(i);
                }
            }
        }

        if(result.size() == n){
            int[] ans = new int[n];
            int count = 0;
            for(int i : result){
                ans[count++] = i;
            }
            return ans;
        }

        return new int[]{};
    }

    public void test(){
        List<List<Integer>> before = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            before.add(new ArrayList<>());
        }

        before.get(0);
        before.get(1).add(6);
        before.get(2).add(5);
        before.get(3).add(6);
        before.get(4).add(3);
        before.get(4).add(6);
        before.get(5);
        before.get(6);
        before.get(7);

        sortItems(8, 2, new int[]{-1,-1,1,0,0,1,0,-1}, before);
    }

}
