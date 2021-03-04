package com.rfb.demo.rxjavatest.asm;


import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;

public class AsmDemo {

    public void test() {
        try {
            ClassReader classReader = new ClassReader("com.rfb.demo.rxjavatest.asm.Music");
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassVisitor visitor = new MyMusicVisitor(writer);
            classReader.accept(visitor, ClassReader.EXPAND_FRAMES);

            byte[] result = writer.toByteArray();


            File file = new File("./target/classes/com/rfb/demo/rxjavatest/asm/Music.class");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(result);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
