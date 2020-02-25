package com.rfb.demo.rxjavatest.funny;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/14 0014.
 */
public class MinMax2 {

    public static Map<TreeNodeKey, TreeNode> sMap = new HashMap<TreeNodeKey, TreeNode>();
    public static List<Integer> sGetParam = new ArrayList<Integer>();

    public static void test(){


        sGetParam.add(5);
        sGetParam.add(2);

        for(int i = 1; i < 30; i++){

            sMap.clear();

            TreeNode root = new TreeNode(i);

            buildTree(root, true);
            minMax(root, true);

            System.out.println(i+" "+TreeNode.count+" "+root.weight);
        }
    }

    public static void buildTree(TreeNode root, boolean isA){

        int value = root.value;
        if(value <= 0){
            if(isA){
                root.weight = -1;
            }else{
                root.weight = 1;
            }
            return;
        }

        for(Integer i:sGetParam){
            int newValue = value-i;
            TreeNodeKey key = new TreeNodeKey(!isA, newValue);
            TreeNode newSon = sMap.get(key);
            if(newSon != null){
                root.sons.add(newSon);
            }else{
                newSon = new TreeNode(newValue);
                root.sons.add(newSon);
                sMap.put(key, newSon);
                buildTree(newSon, !isA);
            }
        }
    }

    public static void minMax(TreeNode root, boolean isA){

        if(root.weight == 0){
            for(TreeNode son:root.sons){
                minMax(son, !isA);
            }
        }else{
            return;
        }

        if(isA){

            int max = Integer.MIN_VALUE;
            for(TreeNode son:root.sons){
                if(son.weight > max){
                    max = son.weight;
                }
            }
            root.weight = max;
        }else{

            int min = Integer.MAX_VALUE;
            for(TreeNode son:root.sons){
                if(min > son.weight){
                    min = son.weight;
                }
            }
            root.weight = min;
        }
    }

    public static class TreeNode{

        public static int count = 0;

        public int value;

        public List<TreeNode> sons;

        public int weight;

        public TreeNode(int value){
            weight = 0;
            this.value = value;
            sons = new ArrayList<TreeNode>();
            count++;
        }
    }

    public static class TreeNodeKey{

        public boolean isA;

        public int value;

        public TreeNodeKey(boolean isA, int value) {
            this.isA = isA;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TreeNodeKey that = (TreeNodeKey) o;

            if (isA != that.isA) return false;
            if (value != that.value) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (isA ? 1 : 0);
            result = 31 * result + value;
            return result;
        }
    }

}
