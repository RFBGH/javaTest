package com.rfb.demo.rxjavatest.asm_inner;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

public class TestClassVisitor extends ClassVisitor {

    public TestClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if(name.equals("<init>")){
            return null;
        }
        return new ExcutorTimeMethodVisistor(api, mv, access, name, descriptor);
    }
}
