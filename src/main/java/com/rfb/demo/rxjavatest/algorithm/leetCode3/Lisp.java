package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class Lisp {

    public enum NodeType{
        key,
        number,
        variable,
        leftBracket,
        rightBracket;
    }

    public enum KeyType{
        LET,
        ADD,
        MULTI;
    }

    private static class Node{
        private NodeType nodeType;

        public Node(NodeType nodeType) {
            this.nodeType = nodeType;
        }
    }

    private static class KeyNode extends Node{

        private KeyType key;

        public KeyNode(KeyType key) {
            super(NodeType.key);
            this.key = key;
        }

        public KeyType getKey() {
            return key;
        }
    }

    private static class NumberNode extends Node{

        private int number;

        public NumberNode(int number) {
            super(NodeType.number);
            this.number = number;
        }

        public int getNumber() {
            return number;
        }
    }

    private static class VariableNode extends Node{

        private String variable;
        private int value;

        public VariableNode(String variable) {
            super(NodeType.variable);
            this.variable = variable;
        }

        public String getVariable() {
            return variable;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static class Context{

        private Map<String, Integer> map = new HashMap<>();
        private Context parent;

        public Context(Context parent) {
            this.parent = parent;
        }

        public Integer get(String key){

            Context context = this;
            while (context != null){
                if(context.map.containsKey(key)){
                    return context.map.get(key);
                }
                context = context.parent;
            }

            throw new RuntimeException("cant find var "+key);
        }

        public void put(String key, Integer value){
            map.put(key, value);
        }
    }

    private static class LeftBracketNode extends Node{

        public LeftBracketNode() {
            super(NodeType.leftBracket);
        }
    }

    private static class RightBracketNode extends Node{

        public RightBracketNode() {
            super(NodeType.rightBracket);
        }
    }

    private List<Node> nodes = new ArrayList<>();
    private int cur = 0;

    private void checkParam(){
        if(cur >= nodes.size()){
            throw new RuntimeException("out of index "+cur+" "+nodes.size());
        }
    }

    private int statement(Context context){

        checkParam();

        Node node = nodes.get(cur++);
        if(node.nodeType != NodeType.leftBracket){
            throw new RuntimeException("node is not leftBracket "+node.nodeType);
        }

        node = nodes.get(cur++);
        if(node.nodeType != NodeType.key){
            throw new RuntimeException("node is not key "+node.nodeType);
        }

        KeyNode keyNode = (KeyNode)node;
        int value = -1;
        switch (keyNode.key){
            case LET:
                value = let(context);
                break;

            case ADD:
                value = add(context);
                break;

            case MULTI:
                value = mult(context);
                break;
        }

        node = nodes.get(cur++);
        if(node.nodeType != NodeType.rightBracket){
            throw new RuntimeException("node is not right bracket "+node.nodeType);
        }

        return value;
    }

    private int let(Context context){

        checkParam();

        Context sonContext = new Context(context);


    }

    private int add(Context context){

        checkParam();

    }

    private int mult(Context context){

        checkParam();

    }

    public int evaluate(String expression) {

        for(int i = 0; i < expression.length(); i++){

            char c = expression.charAt(i);
            switch (c){

                case '(':
                    nodes.add(new LeftBracketNode());
                    break;

                case ')':
                    nodes.add(new RightBracketNode());
                    break;

                case ' ':
                    break;

                default:

                    if(c >= '0' && c <='9'){
                        int number = c - '0';
                        int k;
                        for(k = i+1; k < expression.length(); k++){
                            char nextC = expression.charAt(k);
                            if(nextC >= '0' && nextC <= '9'){
                                number = number*10 + nextC-'0';
                            }else{
                                break;
                            }
                        }

                        nodes.add(new NumberNode(number));
                        i = k-1;
                    }else{

                        StringBuilder sb = new StringBuilder();
                        sb.append(c);
                        int k;
                        for(k = i+1; k < expression.length(); k++){
                            char nextC = expression.charAt(k);
                            if(nextC == ' ' || nextC == ')'){
                                break;
                            }
                            sb.append(nextC);
                        }

                        String value = sb.toString();
                        if(value.equals("add")){
                            nodes.add(new KeyNode(KeyType.ADD));
                        }else if(value.equals("let")){
                            nodes.add(new KeyNode(KeyType.LET));
                        }else if(value.equals("mult")){
                            nodes.add(new KeyNode(KeyType.MULTI));
                        }else{
                            nodes.add(new VariableNode(value));
                        }

                        i = k-1;
                    }

                    break;
            }
        }

        return statement(null);
    }

    public void test(){
        System.out.println(evaluate("(let x 2 (add (let x 3 (let x 4 x)) x))"));
    }

}
