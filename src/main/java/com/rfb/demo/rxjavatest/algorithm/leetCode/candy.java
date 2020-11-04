package com.rfb.demo.rxjavatest.algorithm.leetCode;

public class candy {

    public int candy(int[] ratings) {
        if(ratings.length == 0){
            return 0;
        }

        int[] give = new int[ratings.length];
        give[0] = 1;
        int sum = 1;
        int cur = 0;
        for(int i = cur+1, size = ratings.length; i < size; i++){
            if(ratings[i] == ratings[i-1]){
                give[i] = 1;
            }else if(ratings[i] > ratings[i-1]){
                give[i] = give[i-1] + 1;
            }else{
                give[i] = 1;
                int k = i - 1;

                if(give[k] == 1){
                    give[k]++;
                }

                while (k > 0){
                    if(ratings[k-1] <= ratings[k]){
                        break;
                    }

                    if(give[k] < give[k-1]){
                        break;
                    }

                    give[k-1] = give[k]+1;
                    k--;
                }
                if(give[i-1] == 1){
                    give[i-1]++;
                }
            }
        }

        return sum;
    }

    public void test(){

        System.out.println(candy(new int[]{1,0,2}));

    }

}
