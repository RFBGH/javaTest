package com.rfb.demo.rxjavatest.funny;

/**
 * Created by Administrator on 2017/11/24 0024.
 */
public class RPNUtils {

    public static final String OP_ADD = "+";

    public static final String OP_SUB = "-";

    public static final String OP_MULTI = "*";

    public static final String OP_DIV = "/";

    public static final String OP_LEFT_BRACKETS = "(";

    public static final String OP_RIGHT_BRACKETS = ")";

    public static final boolean isOp(String expressionSeq){

        if(OP_ADD.equals(expressionSeq)){
            return true;
        }

        if(OP_SUB.equals(expressionSeq)){
            return true;
        }

        if(OP_DIV.equals(expressionSeq)){
            return true;
        }

        if(OP_MULTI.equals(expressionSeq)){
            return true;
        }

        if(OP_LEFT_BRACKETS.equals(expressionSeq)){
            return true;
        }

        if(OP_RIGHT_BRACKETS.equals(expressionSeq)){
            return true;
        }

        return false;
    }

}
