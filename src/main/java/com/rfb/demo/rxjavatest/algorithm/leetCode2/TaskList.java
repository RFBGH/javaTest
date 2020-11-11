package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.*;

public class TaskList {


    private static class Task{
        int index;
        int PM;
        int comeOutTime;
        int level;
        int time;
        int restTime;
        public Task(int index, int PM, int comeOutTime, int level, int time){
            this.index = index;
            this.PM = PM;
            this.comeOutTime = comeOutTime;
            this.level = level;
            this.time = time;
            restTime = time;
        }
    }

    public static void test(String[] args){

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int p = scanner.nextInt();

        Task[] taskMap = new Task[p+1];
        List<Task> tasks = new ArrayList<>();

        int minComeoutTime = Integer.MAX_VALUE;
        for(int i = 0; i < p; i++){
            int PM = scanner.nextInt();
            int comeOutTime = scanner.nextInt();
            int level = scanner.nextInt();
            int time = scanner.nextInt();
            if(minComeoutTime > comeOutTime){
                minComeoutTime = comeOutTime;
            }

            Task task = new Task(i+1, PM, comeOutTime, level, time);
            taskMap[i+1] = task;
            tasks.add(task);
        }

        List<Task>[] PM = new ArrayList[n+1];
        for(int i = 0; i < n; i++){
            PM[i+1] = new ArrayList<>();
        }

        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {

                if(o1.level > o2.level){
                    return -1;
                }

                if(o1.level < o2.level){
                    return 1;
                }

                if(o1.time < o2.time){
                    return -1;
                }

                if(o1.time > o2.time){
                    return 1;
                }

                if(o1.comeOutTime < o2.comeOutTime){
                    return -1;
                }

                if(o1.comeOutTime > o2.comeOutTime){
                    return 1;
                }

                return 0;
            }
        });

        for(Task task : tasks){
            PM[task.PM].add(task);
        }

        int[] coders = new int[m+1];
        int[] endTime = new int[p+1];

        int allTimeOffset = minComeoutTime;
        while (true){

            int minLastInCoder = Integer.MAX_VALUE;
            for(int i = 1; i <= m; i++){
                if(coders[i] == 0){

                    Task target = null;
                    for(int j = 1; j <= n; j++){
                        List<Task> temp = PM[j];
                        for(Task task : temp){
                            if(task.comeOutTime > allTimeOffset){
                                continue;
                            }

                            if(target == null){
                                target = task;
                            }else{
                                if(target.time > task.time){
                                    target = task;
                                }
                            }
                            break;
                        }
                    }

                    if(target != null){
                        PM[target.PM].remove(target);
                        coders[i] = target.index;
                    }
                }

                if(coders[i] != 0){
                    Task task = taskMap[coders[i]];
                    if(minLastInCoder > task.restTime){
                        minLastInCoder = task.restTime;
                    }
                }
            }

            if(minLastInCoder == Integer.MAX_VALUE){

                allTimeOffset = Integer.MAX_VALUE;
                for(Task task : tasks){
                    if(task.restTime == 0){
                        continue;
                    }

                    if(task.comeOutTime < allTimeOffset){
                        allTimeOffset = task.comeOutTime;
                    }
                }

                if(allTimeOffset == Integer.MAX_VALUE){
                    break;
                }
                continue;
            }

            allTimeOffset += minLastInCoder;
            for(int i = 1; i <= m; i++){
                if(coders[i] == 0){
                    continue;
                }
                Task task = taskMap[coders[i]];
                task.restTime -= minLastInCoder;
                if(task.restTime == 0){
                    endTime[coders[i]] = allTimeOffset;
                    coders[i] = 0;
                }
            }
        }

        for(int i = 1; i <= p; i++){
            System.out.println(endTime[i]);
        }
    }

}
