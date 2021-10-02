package com.yongha.example;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

class DynamicProxyTest {

    @Test
    public void dynamicProxyTest() {
        DynamicProxyInterface dynamicProxy = (DynamicProxyInterface) Proxy.newProxyInstance(
                DynamicProxyInterface.class.getClassLoader(),
                new Class[]{DynamicProxyInterface.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        boolean haveMyAnnotation = false;
                        if (args != null) {
                            for (Object object : args) {
                                System.out.println(object);
                            }
                        }
                        if (method.getAnnotations() != null) {
                            for (Annotation annotation :method.getAnnotations()) {
                                System.out.println(annotation.annotationType());
                                if (annotation instanceof MyAnnotation) {
                                    haveMyAnnotation = true;
                                }
                            }
                        }
                        System.out.println("invoke method=" + method.getName() + " haveMyAnnotation=" + haveMyAnnotation);
                        return 1;
                    }
                }
        );

        int value = dynamicProxy.hello();
        System.out.println("value=" + value);
        dynamicProxy.hello(111);
        dynamicProxy.hi();
        dynamicProxy.bye();
    }
}