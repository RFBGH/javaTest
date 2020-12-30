package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        public VariableNode(String variable) {
            super(NodeType.variable);
            this.variable = variable;
        }

        public String getVariable() {
            return variable;
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

        int result = -1;

        Node node = nodes.get(cur++);
        if(node.nodeType != NodeType.variable){
            throw new RuntimeException("left node need variable "+node.nodeType);
        }

        VariableNode variableNode = (VariableNode)node;
        sonContext.put(variableNode.getVariable(), getOperatorNum(sonContext));

        while (true){

            node = nodes.get(cur++);
            if(node.nodeType != NodeType.variable){

                if(node.nodeType == NodeType.leftBracket){
                    cur--;
                    result = statement(sonContext);
                    break;
                }

                if(node.nodeType == NodeType.number){
                    result = ((NumberNode)node).number;
                    break;
                }

                throw new RuntimeException("left last expr need expr "+node.nodeType);
            }

            variableNode = (VariableNode) node;
            Node nextNode = nodes.get(cur++);
            if(nextNode.nodeType == NodeType.rightBracket){
                result = sonContext.get(variableNode.getVariable());
                cur--;
                break;
            }

            cur--;
            sonContext.put(variableNode.getVariable(), getOperatorNum(sonContext));
        }

        return result;
    }

    private int getOperatorNum(Context context){

        Node node = nodes.get(cur++);
        if(node.nodeType == NodeType.number){
            return ((NumberNode)node).getNumber();
        }

        if(node.nodeType == NodeType.variable){
            VariableNode variableNode = (VariableNode)node;
            return context.get(variableNode.getVariable());
        }

        if(node.nodeType == NodeType.leftBracket){
            cur--;
            return statement(context);
        }

        throw new RuntimeException("operator is not number variable leftbracket "+node.nodeType);
    }

    private int add(Context context){
        checkParam();
        return getOperatorNum(context) + getOperatorNum(context);
    }

    private int mult(Context context){
        checkParam();
        return getOperatorNum(context) * getOperatorNum(context);
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

                    if(c >= '0' && c <='9' || c == '-'){

                        boolean isNegative = false;
                        int number = 0;
                        if(c == '-'){
                            isNegative = true;
                        }else{
                            number = c - '0';
                        }

                        int k;
                        for(k = i+1; k < expression.length(); k++){
                            char nextC = expression.charAt(k);
                            if(nextC >= '0' && nextC <= '9'){
                                number = number*10 + nextC-'0';
                            }else{
                                break;
                            }
                        }

                        if(isNegative){
                            number = -number;
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
        System.out.println(evaluate("(let x 7 -12)"));
    }

}
