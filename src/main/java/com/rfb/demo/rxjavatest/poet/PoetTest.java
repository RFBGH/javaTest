package com.rfb.demo.rxjavatest.poet;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;

/**
 * Created by Administrator on 2018/5/2 0002.
 */
public class PoetTest {

    public void helloTest(){


    // `JavaFile` 代表 Java 文件
        JavaFile javaFile = JavaFile.builder("com.walfud.howtojavapoet",
                // TypeSpec 代表一个类
                TypeSpec.classBuilder("Clazz")
                        // 给类添加一个属性
                        .addField(FieldSpec.builder(int.class, "mField", Modifier.PRIVATE)
                                .build())
                                // 给类添加一个方法
                        .addMethod(MethodSpec.methodBuilder("method")
                                .addParameter(String.class, "str")
                                .addModifiers(Modifier.PUBLIC)
                                .returns(void.class)
                                .addStatement("System.out.println(str)")
                                .build())
                        .build())
                .build();

        System.out.println(javaFile.toString());
    }

}
