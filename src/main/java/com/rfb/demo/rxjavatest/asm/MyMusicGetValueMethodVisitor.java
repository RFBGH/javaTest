package com.rfb.demo.rxjavatest.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static com.rfb.demo.rxjavatest.asm.MyMusicRunMethodVisitor.hack;

public class MyMusicGetValueMethodVisitor extends MethodAdapter {

    public MyMusicGetValueMethodVisitor(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void visitInsn(int opcode) {
        System.out.println("opcode==" + opcode);
        if (opcode == Opcodes.IRETURN) {
            hack(mv, "insert before return");
        }
        super.visitInsn(opcode);
    }
}
