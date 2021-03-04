package com.rfb.demo.rxjavatest.asm_inner;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class InnerAsmDemo {

    public void test(){

        try {
            ClassReader classReader = new ClassReader("com.rfb.demo.rxjavatest.asm_inner.Test");
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            classReader.accept(new TestClassVisitor(Opcodes.ASM7, writer), ClassReader.EXPAND_FRAMES);

            File file = new File("./target/classes/com/rfb/demo/rxjavatest/asm_inner/Test.class");
            FileOutputStream outputStream = new FileOutputStream(file);

            byte[] result = writer.toByteArray();
            outputStream.write(result);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fire(){

        InnerAsmDemo demo = new InnerAsmDemo();
        demo.test();
        Test test = new Test();
        test.test1();
        test.test2();
        test.test3();
        test.test();

    }

}
