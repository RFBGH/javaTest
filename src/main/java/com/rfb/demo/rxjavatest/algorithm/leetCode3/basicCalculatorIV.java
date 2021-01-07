package com.rfb.demo.rxjavatest.algorithm.leetCode3;

import java.util.*;

public class basicCalculatorIV {

    private enum NodeType{
        LEFT_BRACKET,
        RIGHT_BRACKET,
        NUM,
        OPERATOR,
        VARIABLE,
        EXPRESS
    }

    private enum OperatorType{
        MULT,
        ADD,
        SUB
    }

    private static class Node{
        private NodeType nodeType;

        public Node(NodeType nodeType) {
            this.nodeType = nodeType;
        }
    }

    private static class NumNode extends Node{

        private int num;
        public NumNode(int num) {
            super(NodeType.NUM);
            this.num = num;
        }
    }

    private static class LeftBracketNode extends Node{

        public LeftBracketNode() {
            super(NodeType.LEFT_BRACKET);
        }
    }

    private static class RightBracketNode extends Node{

        public RightBracketNode() {
            super(NodeType.RIGHT_BRACKET);
        }
    }

    private static class OperatorNode extends Node{

        private OperatorType operatorType;
        public OperatorNode(OperatorType operatorType) {
            super(NodeType.OPERATOR);
            this.operatorType = operatorType;
        }
    }

    private static class VariableNode extends Node{

        private String variable;
        public VariableNode(String variable) {
            super(NodeType.VARIABLE);
            this.variable = variable;
        }
    }

    private static class ExpressNode extends Node{

        private List<Node> nodes;
        public ExpressNode(List<Node> nodes) {
            super(NodeType.EXPRESS);
            this.nodes = nodes;
        }
    }

    private enum ResultNodeType{
        NUM,
        EXPRESS
    }
    private static class ResultNode{
        private ResultNodeType resultNodeType;

        public ResultNode(ResultNodeType resultNodeType) {
            this.resultNodeType = resultNodeType;
        }
    }

    private static class NumResultNode extends ResultNode{

        private int value;
        public NumResultNode(int value) {
            super(ResultNodeType.NUM);
            this.value = value;
        }
    }

    private static class ExpressResultNode extends ResultNode{

        private String express;
        public ExpressResultNode(String express) {
            super(ResultNodeType.EXPRESS);
            this.express = express;
        }
    }

    private List<Node> nodes = new ArrayList<>();
    private int cur = 0;

    private List<Node> statment(){

        List<Node> nodeList = new ArrayList<>();

        while (cur < nodes.size()){
            boolean finish = false;
            Node node = nodes.get(cur++);
            switch (node.nodeType){
                case LEFT_BRACKET:
                    statment();
                    break;

                case OPERATOR:

                    OperatorNode operatorNode = (OperatorNode)node;
                    Node param = nodeList.get(nodeList.size()-1);
                    nodeList.remove(nodeList.size()-1);
                    switch (operatorNode.operatorType){
                        case ADD:
                            nodeList.addAll(add(param));
                            break;

                        case SUB:
                            nodeList.addAll(sub(param));
                            break;

                        default:
                            nodeList.addAll(mult(param));
                            break;
                    }

                    break;

                case RIGHT_BRACKET:
                    finish = true;
                    break;

                default:
                    nodeList.add(node);
                    break;
            }

            if(finish){
                break;
            }
        }

        return nodeList;
    }


    private List<Node> add(Node param){

        return null;
    }

    private List<Node> sub(Node param){
        return null;
    }

    private List<Node> mult(Node param){
        return null;
    }

    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {

        Map<String, Integer> variableMap = new HashMap<>();
        for(int i = 0; i < evalints.length; i++){
            variableMap.put(evalvars[i], evalints[i]);
        }

        List<Node> nodes = new ArrayList<>();

        for(int i = 0; i < expression.length(); i++){

            char c = expression.charAt(i);
            switch (c){
                case '(':
                    nodes.add(new LeftBracketNode());
                    break;

                case ')':
                    nodes.add(new RightBracketNode());
                    break;

                case '*':
                    nodes.add(new OperatorNode(OperatorType.MULT));
                    break;

                case '+':
                    nodes.add(new OperatorNode(OperatorType.ADD));
                    break;

                case '-':
                    nodes.add(new OperatorNode(OperatorType.SUB));
                    break;

                case ' ':
                    break;

                default:

                    if(c >= '0' && c <= '9'){

                        int value = 0;
                        while (i < expression.length()){
                            c = expression.charAt(i++);
                            if(c < '0' || c > '9'){
                                break;
                            }

                            value = value * 10 + c - '0';
                        }

                        if(i != expression.length()){
                            i -= 2;
                        }
                        nodes.add(new NumNode(value));
                    }else{

                        StringBuilder sb = new StringBuilder();

                        while (i < expression.length()){
                            c = expression.charAt(i++);
                            if(c < 'a' || c > 'z'){
                                break;
                            }
                            sb.append(c);
                        }
                        if(i != expression.length()){
                            i -= 2;
                        }

                        String variable = sb.toString();
                        if (variableMap.containsKey(variable)) {
                            nodes.add(new NumNode(variableMap.get(variable)));
                        }else{
                            nodes.add(new VariableNode(variable));
                        }
                    }
                    break;
            }
        }

        return null;
    }

    public void test(){
        basicCalculatorIV("e + 8 - a + 5", new String[]{"e"}, new int[]{1});
    }


}
