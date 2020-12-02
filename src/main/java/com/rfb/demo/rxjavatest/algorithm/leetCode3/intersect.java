package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class intersect {

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };

    private void dfs(Node quadTree1, Node quadTree2){

        if(quadTree1 == null || quadTree2 == null){
            return;
        }


        if(quadTree1.isLeaf && quadTree2.isLeaf){
            quadTree1.val = quadTree1.val || quadTree2.val;
            return;
        }

        if(quadTree1.isLeaf){
            quadTree1.isLeaf = false;

            quadTree1.topLeft = new Node(quadTree1.val, true, null, null, null, null);
            quadTree1.topRight = new Node(quadTree1.val, true, null, null, null, null);
            quadTree1.bottomLeft = new Node(quadTree1.val, true, null, null, null, null);
            quadTree1.bottomRight = new Node(quadTree1.val, true, null, null, null, null);
        }

        if(quadTree2.isLeaf){
            quadTree2.isLeaf = false;

            quadTree2.topLeft = new Node(quadTree2.val, true, null, null, null, null);
            quadTree2.topRight = new Node(quadTree2.val, true, null, null, null, null);
            quadTree2.bottomLeft = new Node(quadTree2.val, true, null, null, null, null);
            quadTree2.bottomRight = new Node(quadTree2.val, true, null, null, null, null);
        }

        dfs(quadTree1.topLeft, quadTree2.topLeft);
        dfs(quadTree1.topRight, quadTree2.topRight);
        dfs(quadTree1.bottomLeft, quadTree2.bottomLeft);
        dfs(quadTree1.bottomRight, quadTree2.bottomRight);

        if(quadTree1.topLeft.isLeaf
                && quadTree1.topRight.isLeaf
                && quadTree1.bottomLeft.isLeaf
                && quadTree1.bottomRight.isLeaf){
            if(quadTree1.topLeft.val
                    && quadTree1.topRight.val
                    && quadTree1.bottomLeft.val
                    && quadTree1.bottomRight.val){
                quadTree1.isLeaf = true;
                quadTree1.val = true;
                quadTree1.topLeft = null;
                quadTree1.topRight = null;
                quadTree1.bottomLeft = null;
                quadTree1.bottomRight = null;
                return;
            }

            if(!quadTree1.topLeft.val
                    && !quadTree1.topRight.val
                    && !quadTree1.bottomLeft.val
                    && !quadTree1.bottomRight.val){
                quadTree1.isLeaf = true;
                quadTree1.val = false;
                quadTree1.topLeft = null;
                quadTree1.topRight = null;
                quadTree1.bottomLeft = null;
                quadTree1.bottomRight = null;
            }
        }
    }

    public Node intersect(Node quadTree1, Node quadTree2) {
        dfs(quadTree1, quadTree2);
        return quadTree1;
    }
}
