package com.zero.two;

public class DefaultQuest implements Quest {
    @Override
    public String sayNo() {
        System.out.println("Hello World");
        return null;
    }
}
