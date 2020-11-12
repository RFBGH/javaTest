package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class addOperators {

    private static String[] op = new String[]{"*","+","-"};
    private List<String> result = new ArrayList<>();

    private void dfs(List<String> items, String rest, int target){

        if(rest == null || rest.equals("")){
            Stack<String> ops = new Stack<>();
            Stack<Long> nums = new Stack<>();
            for(String item : items){
                if(item.equals("*")
                    || item.equals("+")
                    || item.equals("-")){
                    ops.push(item);
                }else{

                    if(item.startsWith("0") && item.length() > 1){
                        return;
                    }

                    Long num = Long.parseLong(item);
                    if(ops.isEmpty()){
                        nums.push(num);
                    }else{
                        String o = ops.peek();
                        if(o.equals("*")){
                            Long num2 = nums.pop();
                            nums.push(num * num2);
                            ops.pop();
                        }else{
                            nums.push(num);
                        }
                    }
                }
            }

            Collections.reverse(ops);
            Collections.reverse(nums);
            while (!ops.isEmpty()){
                String o = ops.pop();
                Long num1 = nums.pop();
                Long num2 = nums.pop();
                if(o.equals("+")){
                    nums.push(num1+num2);
                }else{
                    nums.push(num1-num2);
                }
            }

            if(nums.pop() == target){
                StringBuilder sb = new StringBuilder();
                for(String item : items){
                    sb.append(item);
                }
                result.add(sb.toString());
            }
            return;
        }

        for(int i = 1, size = rest.length(); i < size; i++){
            String item = rest.substring(0, i);
            items.add(item);
            String newRest = rest.substring(i, size);
            for(int k = 0; k < 3; k++){
                items.add(op[k]);
                dfs(items, newRest, target);
                items.remove(items.size()-1);
            }
            items.remove(items.size()-1);
        }

        items.add(rest);
        dfs(items, "", target);
        items.remove(items.size()-1);

    }

    public List<String> addOperators(String num, int target) {

        if(num == null || num.length() == 0){
            return result;
        }

        dfs(new ArrayList<>(), num, target);
        return result;
    }

    public void test(){

        addOperators("105", 5);
//        addOperators("123456789", 45);
    }
}
