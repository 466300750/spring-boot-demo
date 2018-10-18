package com.dynamicproxy;

import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IHello iHello = (IHello) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(),
            new Class[]{IHello.class},
            new ProxyHandler(new Hello()));
        iHello.sayHello();
    }
}
