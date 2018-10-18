package com.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy = " + proxy.getClass());
        System.out.println("method = " + method.getName());
        System.out.println("before came into proxy method");

        Object rs = method.invoke(target, args);
        System.out.println("after invoke base class method");
        return rs;
    }
}
