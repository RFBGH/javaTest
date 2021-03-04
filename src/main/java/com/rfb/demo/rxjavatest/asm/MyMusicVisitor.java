package com.rfb.demo.rxjavatest.asm;

import org.objectweb.asm.*;

public class MyMusicVisitor extends ClassAdapter {


    public MyMusicVisitor(ClassWriter writer){
        super(writer);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

        System.out.println("=====================");
        System.out.println("acce== " + access);
        System.out.println("name== " + name);
        System.out.println("desc== " + desc);
        System.out.println("sign== " + signature);
        System.out.println("=====================");

        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("run")) {
            mv = new MyMusicRunMethodVisitor(mv);
        }

        if (name.equals("getValue")) {
            mv = new MyMusicGetValueMethodVisitor(mv);
        }

        return mv;
    }
}
