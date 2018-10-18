package com.spring;

public class HelloBean {

    private String name;

    // 构造函数
    public HelloBean() {
        System.out.println("hello constructor");
    }

    static {
        System.out.println("hello static block");
    }

    public void init() {
        System.out.println("hello init");
    }

    public void say() {
        System.out.println("hello " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("set name value =" + name);
        this.name = name;
    }
}
