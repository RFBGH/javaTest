package com.rfb.demo.rxjavatest.algorithm.leetCode3;


import java.util.*;

public class LFUCache {

    public static int time = 0;

    public static class MinHeap<T>{

        public interface ICompare<T>{
            int compare(T t1, T t2);
        }

        private int mCapacity;
        private List<T> mData;
        private MinHeap.ICompare mCompare;

        public MinHeap(int capacity, MinHeap.ICompare compare){
            mCapacity = capacity;
            mData = new ArrayList<>(capacity);
            mCompare = compare;
        }

        public T poll(){

            T head = mData.get(0);
            T temp = mData.get(mData.size()-1);

            mData.set(0, temp);
            int cur = 0;
            while (true){

                int left = cur*2 + 1;
                if(left >= mData.size()){
                    break;
                }
                int minSon = left;

                int right = cur*2 + 2;
                if(right < mData.size()){
                    if(mCompare.compare(mData.get(left), mData.get(right)) > 0){
                        minSon = right;
                    }
                }

                if(mCompare.compare(mData.get(cur), mData.get(minSon)) <= 0){
                    break;
                }

                temp = mData.get(cur);
                mData.set(cur, mData.get(minSon));
                mData.set(minSon, temp);

                cur = minSon;
            }

            mData.remove(mData.size()-1);
            return head;
        }

        public void add(T t){
            mData.add(t);
            int cur = mData.size()-1;

            while (cur != 0){
                int parent = (cur - 1) / 2;
                if(mCompare.compare(t, mData.get(parent)) < 0){
                    mData.set(cur, mData.get(parent));
                    mData.set(parent, t);
                    cur = parent;
                }else{
                    break;
                }
            }
        }

        public void update(T t){

            int cur = mData.indexOf(t);
            while (true){

                int left = cur*2 + 1;
                if(left >= mData.size()){
                    break;
                }

                int minSon = left;
                int right = cur*2 + 2;
                if(right < mData.size()){
                    if(mCompare.compare(mData.get(left), mData.get(right)) > 0){
                        minSon = right;
                    }
                }

                if(mCompare.compare(mData.get(cur), mData.get(minSon)) <= 0){
                    break;
                }

                T temp = mData.get(cur);
                mData.set(cur, mData.get(minSon));
                mData.set(minSon, temp);

                cur = minSon;
            }
        }

        public boolean isEmpty(){
            return mData.isEmpty();
        }
    }


    private Map<Integer, Node> map = new HashMap<>();
    private MinHeap<Node> minHeap;
    private static class Node{
        int key;
        int value;
        int count;
        long time;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.count = 1;
            this.time = LFUCache.time;
        }
    }

    public LFUCache(int capacity) {
        minHeap = new MinHeap<Node>(capacity, new MinHeap.ICompare<Node>() {
            @Override
            public int compare(Node t1, Node t2) {

                if(t1.count < t2.count){
                    return -1;
                }

                if(t1.count > t2.count){
                    return 1;
                }

                if(t1.time < t2.time){
                    return -1;
                }

                if(t1.time > t2.time){
                    return 1;
                }

                return 0;
            }
        });
    }

    public int get(int key) {

        time++;

        if(minHeap.mCapacity == 0){
            return -1;
        }

        Node node = map.get(key);
        if(node == null){
            return -1;
        }

        node.time = time;
        node.count++;
        minHeap.update(node);
        return node.value;
    }

    public void put(int key, int value) {
        time++;

        if(minHeap.mCapacity == 0){
            return;
        }

        Node node = map.get(key);
        if(node != null){
            node.value = value;
            node.count++;
            node.time = time;
            minHeap.update(node);
        }else{

            node = new Node(key, value);
            if(map.size() < minHeap.mCapacity){
                minHeap.add(node);
                map.put(key, node);
            }else{
                Node min = minHeap.poll();
                map.remove(min.key);
                minHeap.add(node);
                map.put(key, node);
            }
        }
    }

    public static void test(){


        LFUCache lFUCache = new LFUCache(2);
        lFUCache.put(1, 1);
        lFUCache.put(2, 2);
        System.out.println(lFUCache.get(1));      // 返回 1
        lFUCache.put(3, 3);   // 去除键 2
        System.out.println(lFUCache.get(2));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3
        lFUCache.put(4, 4);   // 去除键 1
        System.out.println(lFUCache.get(1));      // 返回 -1（未找到）
        System.out.println(lFUCache.get(3));      // 返回 3
        System.out.println(lFUCache.get(4));      // 返回 4
    }
}
