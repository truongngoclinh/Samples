package com.linhtruong.sample.test;

/**
 * @author linhtruong
 */
public class Outer {
    private String name = "Just a name";

    class Inner {
        private void printName() {
            System.out.println(name);
        }
    }
}
