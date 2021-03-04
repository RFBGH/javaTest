package com.rfb.demo.rxjavatest.asm;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.PrintStream;

public class MyMusicRunMethodVisitor extends MethodAdapter {

    public MyMusicRunMethodVisitor(MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();

        System.out.println("start hack before");
        hack(mv, "asm insert before");
    }

    @Override
    public void visitInsn(int opcode) {
        if(opcode == Opcodes.RETURN){
            System.out.println("start hack after");
            hack(mv, "asm insert after");
        }
        super.visitInsn(opcode);
    }

    public static void hack(MethodVisitor mv, String s){
        mv.visitFieldInsn(
                Opcodes.GETSTATIC,
                Type.getInternalName(System.class),
                "out",
                Type.getDescriptor(PrintStream.class)
        );
        mv.visitLdcInsn(s);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                Type.getInternalName(PrintStream.class),
                "println",
                "(Ljava/lang/String;)V"
        );
    }
}
