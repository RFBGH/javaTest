package com.rfb.demo.rxjavatest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/6/19 0019.
 */
public class UpArray {

    private static class NodeInfo{
        public int count;
        public int parent;
    }

    public static void find(List<Integer> data){

        List<NodeInfo> nodeInfos = new ArrayList<>();

        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.count = 1;
        nodeInfo.parent = -1;
        nodeInfos.add(nodeInfo);

        int MAX = -1;
        int ans = 0;

        for(int i = 1, size = data.size(); i < size; i++){

            int value = data.get(i);
            int max = 1;
            int parent = -1;
            for(int k = 0; k < i; k++){

                if(data.get(k) >= value){
                    continue;
                }

                NodeInfo lastNode = nodeInfos.get(k);
                int count = lastNode.count+1;
                if(count <= max){
                    continue;
                }

                max = count;
                parent = k;
            }

            NodeInfo newNode = new NodeInfo();
            newNode.parent = parent;
            newNode.count = max;
            nodeInfos.add(newNode);

            if(MAX < max){
                MAX = max;
                ans = i;
            }
        }

        System.out.println(MAX+" "+ans);
        while(ans != -1){
            System.out.print(ans+" ");
            ans = nodeInfos.get(ans).parent;
        }
        System.out.println();

    }

}
