package com.yongha.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteModifyMain {
    public static void main(String[] args) {
        try {
            new ByteBuddy().redefine(ByteModify.class)
                    .method(named("getString")).intercept(FixedValue.value("bye"))
                    .make().saveIn(new File("build\\classes\\java\\main"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteModify byteModify = null;
        try {
            Class klass = Class.forName("com.yongha.example.ByteModify");
            byteModify = (ByteModify) klass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(byteModify.getString());
    }
}
