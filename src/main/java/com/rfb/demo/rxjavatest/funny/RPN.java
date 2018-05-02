package com.rfb.demo.rxjavatest.funny;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Administrator on 2017/11/24 0024.
 */
public class RPN {

    public void test(String expression){

        List<ExpressionSeq> expressionSeqs = new ArrayList<ExpressionSeq>();

        String expressionSeq[] = expression.split(" ");
        for(int i = 0, size = expressionSeq.length; i < size; i++){

            ExpressionSeq expressSeq = new ExpressionSeq();
            expressSeq.value = expressionSeq[i];
            if(RPNUtils.isOp(expressionSeq[i])){
                expressSeq.type = ExpressionSeqType.op;
            }else{
                expressSeq.type = ExpressionSeqType.num;
            }

            expressionSeqs.add(expressSeq);
        }

        List<ExpressionSeq> rpnExpSeqs = rpn(expressionSeqs);
        for(ExpressionSeq seq:rpnExpSeqs){
            System.out.print(seq.value+" ");
        }
        System.out.println();
    }

    public List<ExpressionSeq> rpn(List<ExpressionSeq> expressionSeqs){

        List<ExpressionSeq> RpnSeq = new ArrayList<ExpressionSeq>();
        Stack<ExpressionSeq> opExpSeqs = new Stack<ExpressionSeq>();

        for(ExpressionSeq currExpSeq:expressionSeqs){

            if(currExpSeq.type == ExpressionSeqType.num){
                RpnSeq.add(currExpSeq);
            }else{

                if(RPNUtils.OP_LEFT_BRACKETS.equals(currExpSeq.value)){
                    opExpSeqs.add(currExpSeq);
                }else if(RPNUtils.OP_RIGHT_BRACKETS.equals(currExpSeq.value)){

                    while(!opExpSeqs.isEmpty()){
                        ExpressionSeq opExpSeq = opExpSeqs.pop();
                        if(RPNUtils.OP_LEFT_BRACKETS.equals(opExpSeq.value)){
                            break;
                        }

                        RpnSeq.add(opExpSeq);
                    }
                }else if(RPNUtils.OP_DIV.equals(currExpSeq.value)
                        || RPNUtils.OP_MULTI.equals(currExpSeq.value)){

                    if(!opExpSeqs.isEmpty()) {
                        ExpressionSeq lastOpExpSeq = opExpSeqs.peek();
                        if(!RPNUtils.OP_LEFT_BRACKETS.equals(lastOpExpSeq.value)){
                            if (RPNUtils.OP_MULTI.equals(lastOpExpSeq.value)
                                    || RPNUtils.OP_DIV.equals(lastOpExpSeq.value)) {
                                RpnSeq.add(lastOpExpSeq);
                                opExpSeqs.pop();
                            }
                        }
                    }
                    opExpSeqs.add(currExpSeq);
                }else {

                    if(!opExpSeqs.isEmpty()){

                        ExpressionSeq lastOpExpSeq = opExpSeqs.peek();
                        if(!RPNUtils.OP_LEFT_BRACKETS.equals(lastOpExpSeq.value)){
                            RpnSeq.add(lastOpExpSeq);
                            opExpSeqs.pop();
                        }
                    }
                    opExpSeqs.add(currExpSeq);
                }
            }
        }

        while(!opExpSeqs.isEmpty()){

            ExpressionSeq opExpSeq = opExpSeqs.pop();
            if(RPNUtils.OP_LEFT_BRACKETS.equals(opExpSeq.value)
                    || RPNUtils.OP_RIGHT_BRACKETS.equals(opExpSeq.value)){
                continue;
            }

            RpnSeq.add(opExpSeq);
        }

        return RpnSeq;
    }

    private static class ExpressionSeq{
        ExpressionSeqType type;
        String value;
    }

    private static enum ExpressionSeqType{
        num,
        op
    }
}
