package com.test;

import com.test.annotation.TestAnnotation;

public class App {

    @TestAnnotation
    private String whatever;

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
