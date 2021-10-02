package com.yongha.example;

public interface DynamicProxyInterface {

    @MyAnnotation
    int hello();
    void hello(int value);
    void bye();
    void hi();
}
