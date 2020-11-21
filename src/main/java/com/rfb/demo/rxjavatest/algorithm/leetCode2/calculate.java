package com.rfb.demo.rxjavatest.algorithm.leetCode2;

import java.util.Stack;

public class calculate {

    public int calculate(String s) {

        Stack<Integer> stack = new Stack<>();
        Stack<Character> opStack = new Stack<>();

        for(int i = 0, n = s.length(); i < n; i++){
            char c = s.charAt(i);
            if(c == ' '){
                continue;
            }

            if(c >= '0' && c <= '9'){
                int temp = c - '0';
                i++;
                while (i < n){
                    c = s.charAt(i);
                    if(c >= '0' && c <= '9'){
                        temp = temp * 10 + c - '0';
                    }else{
                        break;
                    }
                    i++;
                }

                if(opStack.isEmpty()){
                    stack.push(temp);
                }else{
                    char op = opStack.peek();
                    if(op == '('){
                        stack.push(temp);
                    }else{

                        int num = stack.pop();
                        if(op == '+'){
                            num += temp;
                        }else{
                            num -= temp;
                        }
                        stack.push(num);
                        opStack.pop();
                    }
                }
                i--;
            }else{
                if(c == ')'){
                    opStack.pop();
                    while (!opStack.isEmpty()){
                        char op = opStack.peek();
                        if(op == '('){
                            break;
                        }

                        int num2 = stack.pop();
                        int num1 = stack.pop();
                        if(op == '+'){
                            num1 += num2;
                        }else{
                            num1 -= num2;
                        }
                        opStack.pop();
                        stack.push(num1);
                    }
                }else{
                    opStack.push(c);
                }
            }
        }

        return stack.pop();
    }

    public void test(){
        System.out.println(calculate("1-(5)"));
    }
}
