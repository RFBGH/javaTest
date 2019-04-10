package com.rfb.demo.rxjavatest.funny;

import java.util.List;

/**
 * Created by Administrator on 2019/4/10 0010.
 */
public class AB_MinMax {

    public static class TreeNode{

        public static final int TYPE_MIN = 1;
        public static final int TYPE_MAX = 0;

        public int type;
        public int bound;

        public TreeNode parent;
        public List<TreeNode> sons;
    }

    public void deal(){



    }

    public int dfs(TreeNode cur){

        if(cur.sons.isEmpty()){
            return cur.bound;
        }

        TreeNode parent = cur.parent;

        if(cur.type == TreeNode.TYPE_MAX){

            int max = Integer.MIN_VALUE;
            for(TreeNode son:cur.sons){

                int bound = dfs(son);
                if(max < bound){
                    max = bound;
                }

                if(max >= parent.bound){
                    break;
                }
            }

            cur.bound = max;
            if(parent.bound > max){
                parent.bound = max;
            }

            return max;
        }else{

            int min = Integer.MAX_VALUE;
            for(TreeNode son:cur.sons){

                int bound = dfs(son);
                if(min > bound){
                    min = bound;
                }

                if(min <= parent.bound){
                    break;
                }
            }

            cur.bound = min;
            if(parent.bound < min){
                parent.bound = min;
            }

            return min;
        }
    }
}

