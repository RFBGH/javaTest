package com.rfb.demo.rxjavatest.algorithm.leetCode3;

public class circularArrayLoop1 {

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for(int i = 0; i < n; i++){

            boolean[] gone = new boolean[n];
            gone[i] = true;
            int cur = i;

            boolean containForward = false;
            boolean containBackward = false;
            boolean countIsOne = false;
            while(true){
                int next = cur + nums[cur];

                if(!containForward && next > cur){
                    containForward = true;
                }

                if(!containBackward && next < cur){
                    containBackward = true;
                }

                if(next >= n){
                    next = next % n;
                }

                if(next < 0){
                    next = n - ((0-next)%n);
                }

                if(gone[next]){
                    countIsOne = next == cur;
                    break;
                }
                gone[next] = true;
                cur = next;
            }

            if(containForward && containBackward){
                continue;
            }

            if(!countIsOne){
                return true;
            }
        }

        return false;
    }

    public void test(){
        System.out.println(circularArrayLoop(new int[]{-1,-2,-3,-4,-5}));
        System.out.println(circularArrayLoop(new int[]{-1,2}));
    }
}
