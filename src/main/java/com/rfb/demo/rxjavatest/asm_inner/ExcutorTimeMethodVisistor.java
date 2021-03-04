package com.rfb.demo.rxjavatest.asm_inner;


import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class ExcutorTimeMethodVisistor extends AdviceAdapter {

    private int startTimeId = 0;
    public ExcutorTimeMethodVisistor(int api, MethodVisitor methodVisitor, int acc, String methodName, String desc) {
        super(api, methodVisitor, acc, methodName, desc);
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();

        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");

        startTimeId = newLocal(Type.LONG_TYPE);
        mv.visitIntInsn(Opcodes.LSTORE, startTimeId);
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);

        mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
        mv.visitVarInsn(Opcodes.LLOAD, startTimeId);
        mv.visitInsn(Opcodes.LSUB);

        int durationId = startTimeId;
        mv.visitVarInsn(Opcodes.LSTORE, durationId);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V");
        mv.visitLdcInsn("The cost time of " + getName() + " is ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;");
        mv.visitVarInsn(Opcodes.LLOAD, durationId);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
    }
}
