package com.rfb.demo.rxjavatest.algorithm.leetCode.segmentTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/18 0018.
 */
public class Leet218{

    private static class Data{
        public int from;
        public int to;
        public int height;

        private Data(int from, int to, int height) {
            this.from = from;
            this.to = to;
            this.height = height;
        }
    }

    private static class SegTree{
        public int from;
        public int to;
        public int height;
        public List<SegTree> sons = new ArrayList<>();

        public SegTree(int from, int to, int height) {
            this.from = from;
            this.to = to;
            this.height = height;
        }

        public boolean isLeaf(){
            return sons.isEmpty();
        }
    }

    public static void insert(SegTree root, Data data){

        if(root.isLeaf()){

            if(data.height < root.height){
                return;
            }

            if(data.from == root.from && data.to == root.to){
                root.height = data.height;
                return;
            }

            int from = root.from;
            int to = data.from;
            if(from != to){
                SegTree son = new SegTree(from, to, root.height);
                root.sons.add(son);
            }

            {
                from = data.from;
                to = data.to;
                SegTree son = new SegTree(from, to, data.height);
                root.sons.add(son);
            }

            from = data.to;
            to = root.to;
            if(from != to){
                SegTree son = new SegTree(from, to, root.height);
                root.sons.add(son);
            }

            return;
        }

        List<SegTree> sons = root.sons;
        for(SegTree son : sons){

            if(data.from >= son.to
                    || data.to <= son.from){
                continue;
            }

            if(data.from >= son.from && data.to <= son.to){
                insert(son, data);
                continue;
            }

            if(data.from <= son.from && data.to >= son.to){
                Data newData = new Data(son.from, son.to, data.height);
                insert(son, newData);
                continue;
            }

            if(data.from <= son.from){
                Data newData = new Data(son.from, data.to, data.height);
                insert(son, newData);
                continue;
            }

            Data newData = new Data(data.from, son.to, data.height);
            insert(son, newData);
        }

    }

    public static List<Data> result = new ArrayList<>();

    public static void printLeaf(SegTree root){

        if(root.isLeaf()){

            if(result.isEmpty()){
                result.add(new Data(root.from, root.to, root.height));
            }else{
                Data last = result.get(result.size()-1);
                if(last.to == root.from && last.height == root.height){
                    last.to = root.to;
                }else{
                    result.add(new Data(root.from, root.to, root.height));
                }
            }

            return;
        }

        List<SegTree> sons = root.sons;
        for(SegTree son : sons){
            printLeaf(son);
        }
    }

    public static void test(){

        SegTree root = new SegTree(0, Integer.MAX_VALUE, 0);

        List<Data> datas = new ArrayList<>();

        datas.add(new Data(2, 9, 10));
        datas.add(new Data(3, 7, 15));
        datas.add(new Data(5, 12, 12));
        datas.add(new Data(15, 20, 10));
        datas.add(new Data(19, 24, 8));

        for(Data data:datas){
            insert(root, data);
        }

        printLeaf(root);

        for(Data data:result){
            System.out.println(data.from+" "+data.to+" "+data.height);
        }

    }

}
