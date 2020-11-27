package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.List;

public class trapRainWater {

    public static class MinHeap<T>{

        public interface ICompare<T>{
            int compare(T t1, T t2);
        }

        private int mCapacity;
        private List<T> mData;
        private ICompare mCompare;

        public MinHeap(int capacity, ICompare compare){
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

        public boolean isEmpty(){
            return mData.isEmpty();
        }
    }

    private static class PosInfo{
        public int height;
        public int i;
        public int j;

        public PosInfo(int height, int i, int j) {
            this.height = height;
            this.i = i;
            this.j = j;
        }
    }
    private int[][] move = new int[][]{{-1, 0},{0,-1},{1,0},{0,1}};

    public int trapRainWater(int[][] heightMap) {

        int n = heightMap.length;
        int m = heightMap[0].length;
        boolean[][] gone = new boolean[n][m];

        MinHeap<PosInfo> minHeap = new MinHeap<>(n * m + 5, new MinHeap.ICompare<PosInfo>() {
            @Override
            public int compare(PosInfo t1, PosInfo t2) {
                if(t1.height < t2.height){
                    return -1;
                }else if(t1.height > t2.height){
                    return 1;
                }else{
                    return 0;
                }
            }
        });

        for(int i = 0; i < n; i++){
            gone[i][0] = true;
            gone[i][m-1] = true;
            minHeap.add(new PosInfo(heightMap[i][0], i, 0));
            minHeap.add(new PosInfo(heightMap[i][m-1], i, m-1));
        }

        for(int j = 1; j < m-1; j++){
            gone[0][j] = true;
            gone[n-1][j] = true;
            minHeap.add(new PosInfo(heightMap[0][j], 0, j));
            minHeap.add(new PosInfo(heightMap[n-1][j], n-1, j));
        }

        int sum = 0;
        while (!minHeap.isEmpty()){
            PosInfo cur = minHeap.poll();
            for(int k = 0; k < 4; k++){
                int i = cur.i + move[k][0];
                int j = cur.j + move[k][1];
                if(i < 0 || i >= n || j < 0 || j >= m){
                    continue;
                }

                if(gone[i][j]){
                    continue;
                }

                gone[i][j] = true;
                if(heightMap[i][j] < cur.height){
                    sum += cur.height - heightMap[i][j];
                    minHeap.add(new PosInfo(cur.height, i, j));
                }else{
                    minHeap.add(new PosInfo(heightMap[i][j], i, j));
                }
            }
        }

        return sum;
    }

    public void test(){
        System.out.println(trapRainWater(new int[][]{{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}}));
    }
}
