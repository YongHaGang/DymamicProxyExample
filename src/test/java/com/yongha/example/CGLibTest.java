package com.yongha.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;

public class CGLibTest {

    @Test
    void test() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CGLibClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
              throws Throwable {
                boolean haveMyAnnotation = false;
                for (Annotation annotation :method.getAnnotations()) {
                    if (annotation instanceof MyAnnotation) {
                        haveMyAnnotation = true;
                    }
                }
                System.out.println("invoke method=" + method.getName() + " haveMyAnnotation=" + haveMyAnnotation
                  + (objects != null && objects.length > 0 ? " arg=" + objects[0] : ""));
                return null;
            }
        });
        CGLibClass cgLibClass = (CGLibClass) enhancer.create();

        cgLibClass.hello();
        cgLibClass.hello(10);
        cgLibClass.bye();

    }
}
