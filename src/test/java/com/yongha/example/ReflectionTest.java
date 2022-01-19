package com.yongha.example;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class ReflectionTest {

    @Test
    void objectReflectionTest() {
        printArray(Object.class.getConstructors());
        System.out.println();
        printArray(Object.class.getMethods());
    }

    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.println(element);
        }
    }

    @Test
    void findReflectionTest() throws ClassNotFoundException {
        printArray(Class.forName("java.lang.String").getConstructors());
    }

    @Test
    void methodTest()
      throws ClassNotFoundException,
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException,
      InstantiationException {
        Class clazz = Class.forName("com.yongha.example.MyClass");
        Method method = clazz.getDeclaredMethod("hello", new Class[]{});
        method.setAccessible(true);
        method.invoke(clazz.newInstance());
    }

    @Test
    void assignTest() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        MyClass myClass = new MyClass();
        Field field = myClass.getClass().getDeclaredField("value");
        field.setInt(myClass, 10);
        myClass.printValue();
    }

}
