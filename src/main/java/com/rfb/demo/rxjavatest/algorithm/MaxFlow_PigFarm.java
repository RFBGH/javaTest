package com.rfb.demo.rxjavatest.algorithm;

/**
 * Created by Administrator on 2020/7/27 0027.
 */
public class MaxFlow_PigFarm {

    private int N = 0;
    private int M = 0;
    private int[] pigs = null;
    private int[] wants = null;
    private boolean[][] G = null;
    private boolean[] useds = null;
    private int[][] keys = null;

    public void init(int N, int M, int[] pigs){
        this.N = N;
        this.M = M;
        this.pigs = pigs;
        wants = new int[N];
        G = new boolean[M][M];
        useds = new boolean[M];
        keys = new int[N][];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < M ;j++){
                G[i][j] = false;
            }
        }
    }

    public void addCustome(int k, int[] key, int want){
        wants[k] = want;
        keys[k] = key;

        for(int i = 0, size = key.length; i < size; i++){
            for(int j = i+1; j < size; j++){
                G[i][j] = true;
            }
        }
    }

    public int dfs(int f, int want){

        if(pigs[f] != 0){
            int get = Math.min(pigs[f], want);
            pigs[f] -= get;
            return get;
        }else{
            useds[f] = true;
            for(int i = 0; i < M; i++){
                if(useds[i]){
                    continue;
                }

                if(!G[f][i]){
                    continue;
                }

                return dfs(i, want);
            }
        }
        return 0;
    }

    private void resetUsed(){
        for(int i = 0; i < M; i++){
            useds[i] = false;
        }
    }

    public void calc(){

        int ans = 0;

        for(int i = 0; i < N; i++){
            int want = wants[i];
            int[] key = keys[i];
            while (true){

                int get = 0;
                for(int j = 0, size = key.length; j < size; j++){
                    resetUsed();
                    get = dfs(key[j], want);
                    if(get != 0){
                        break;
                    }
                }

                System.out.print(i+" "+get);
                System.out.println();

                if(get == 0){
                    break;
                }

                want -= get;
                ans += get;

                if(want <= 0 ){
                    break;
                }
            }
        }

        System.out.println(ans);
    }



    public static void test(){

        MaxFlow_PigFarm maxFlow_pigFarm = new MaxFlow_PigFarm();
        maxFlow_pigFarm.init(3, 3, new int[]{3, 1, 10});
        maxFlow_pigFarm.addCustome(0, new int[]{0, 1}, 2);
        maxFlow_pigFarm.addCustome(1, new int[]{0, 2}, 3);
        maxFlow_pigFarm.addCustome(2, new int[]{2}, 6);

        maxFlow_pigFarm.calc();
    }

}
