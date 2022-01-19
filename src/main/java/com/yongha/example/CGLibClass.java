package com.yongha.example;

public class CGLibClass {
    @MyAnnotation
    void hello() {
        System.out.println("hello");
    };
    void hello(int value) {
        System.out.println("hello " + value);
    };
    void bye() {
        System.out.println("bye");
    };
}
