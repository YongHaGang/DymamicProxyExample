package com.yongha.example;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

class ByteModifyTest {
    @Test
    public void byteBuddyTest() {
        try {
            new ByteBuddy().redefine(ByteModify.class)
                    .method(named("getString")).intercept(FixedValue.value("bye"))
                    .make().saveIn(new File("D:\\workspace\\retorifit\\DynamicProxyExample\\build\\classes\\java\\main"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteModify byteModify = new ByteModify();
        System.out.println(byteModify.getString());
    }
}