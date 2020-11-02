package com.rfb.demo.rxjavatest.algorithm.leetCode;

import java.io.*;
import java.util.*;

public class Jump {

    private static class Node{
        int pos;
        int step;

        public Node(int pos, int step) {
            this.pos = pos;
            this.step = step;
        }
    }

    public int jump(int[] nums) {

        if(nums.length == 1){
            return 0;
        }

        boolean[] used = new boolean[nums.length];
        for(int i = 0, size = nums.length; i < size; i++){
            used[i] = false;
        }

        int size = nums.length;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));

        int longest = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int length = cur.pos + nums[cur.pos];

            for(int i = longest; i <= length; i++){
                int pos = i;
                if(used[pos]){
                    continue;
                }

                used[pos] = true;
                if(pos > longest){
                    longest = pos;
                }

                int step = cur.step + 1;
                if(pos == size-1){
                    return step;
                }

                queue.add(new Node(pos, step));
            }
        }

        return 0;
    }

    public void test(){

        File f = new File("F://a.txt");
        try {
            FileInputStream inputStream = new FileInputStream(f);
            byte[] data = new byte[1024];

            StringBuilder sb = new StringBuilder();
            while (true){
                int len = inputStream.read(data, 0, 1024);
                if(len <= 0){
                    break;
                }

                for(int i = 0; i < len; i++){
                    sb.append((char) data[i]);
                }
            }

            inputStream.close();
            System.out.println(sb);

            String[] strNums = sb.toString().split(",");
            int[] nums = new int[strNums.length];
            for(int i = 0, size = strNums.length; i < size; i++){
                nums[i] = Integer.parseInt(strNums[i]);
            }

            System.out.println(jump(nums));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
