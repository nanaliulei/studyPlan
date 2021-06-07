package com.liuliu.dynamic;

import com.liuliu.dynamic.handler.MyInvocationHandler;
import com.liuliu.dynamic.pojo.Person;
import com.liuliu.dynamic.pojo.impl.Man;

import java.lang.reflect.Proxy;

/**
 * @Author: liulei
 * @Time: 2021/3/19 15:19
 * @Description
 */

public class DynamicProxyTest {

    public static void main(String[] args) {
        Person man = new Man();
        ClassLoader classLoader = man.getClass().getClassLoader();
        MyInvocationHandler handler = new MyInvocationHandler(man);
        Person proxy = (Person) Proxy.newProxyInstance(DynamicProxyTest.class.getClassLoader(), new Class[]{Person.class}, handler);
        proxy.talk();
//        proxy.sleep();
    }
}
