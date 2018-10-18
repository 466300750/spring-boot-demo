package com.dynamicproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;

public class TimingMethodInterceptor implements MethodInterceptor {

    public <T> T getProxy(Class<T> cls) {
        T t = (T) Enhancer.create(cls, this);
        return t;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        long start = System.nanoTime();
        Object rs = methodProxy.invokeSuper(o, args);
        long elapsed = System.nanoTime() - start;

        System.out.printf("Executing %s finished in %d ns", method.getName(), elapsed);
        return rs;
    }

    public static void main(String[] args) {
        TimingMethodInterceptor timingMethodInterceptor = new TimingMethodInterceptor();
        HashMap hashMap = timingMethodInterceptor.getProxy(HashMap.class);
        hashMap.put(0, 0);
        hashMap.get(0);
    }
}
