package com.rfb.demo.rxjavatest.reflect;

import com.rfb.demo.rxjavatest.bean.Action1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by Administrator on 2018/7/23 0023.
 */
public class FinalStaticChangeTest {

    public static final Action1 sAction = new DefaultAction();


    private static class DefaultAction implements Action1{

        @Override
        public void call() {
            System.out.println("it is default action1");
        }
    }

    private static class ChangeAction implements Action1{

        @Override
        public void call() {
            System.out.println("it is change action1");
        }
    }

    public static void testChangeMethod(){

        try{
            Field actionField = FinalStaticChangeTest.class.getField("sAction");

            actionField.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(actionField, actionField.getModifiers() & ~Modifier.FINAL);
            actionField.set(Action1.class, new ChangeAction());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void test(){

        FinalStaticChangeTest.sAction.call();
        FinalStaticChangeTest.testChangeMethod();
        FinalStaticChangeTest.sAction.call();
    }
}
