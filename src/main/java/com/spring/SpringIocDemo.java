package com.spring;

import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class SpringIocDemo {

    public static void main(String args[]) {
        // 读取xml配置文件，Resource接口主要与文件打交道
        ClassPathResource resource = new ClassPathResource("spring.xml");
        // XmlBeanFactory = DefaultListableBeanFactory + XmlBeanDefinitionReader
        // 调用XmlBeanDefinitionReader的loadBeanDefinitions()方法，此时只是解释成BeanDefinition,
        // 并加载到beanDefinitionMap中
        XmlBeanFactory factory = new XmlBeanFactory(resource);
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        ((AbstractBeanFactory) factory).addBeanPostProcessor(beanPostProcessor);

        // 到这里还没有实例成bean，普通的BeanFactory不管你是否设置lazy-init属性，都不会实例化bean，只有在getBean时才会实例化
        // 先执行静态代码块-->构造函数--->set注入方法--->BeanPostProcessor
        // beforeInit()方法--->init()方法--->BeanPostProcessor afterInit()
        // --->调用bean中的方法
        // 先createBean()创建bean，然后populateBean()依赖注入,initializeBean()调用beanPostProcessor及init()方法
        HelloBean helloBean = (HelloBean) factory.getBean("hellobean");
        // 调用bean的方法
        helloBean.say();
    }
}
