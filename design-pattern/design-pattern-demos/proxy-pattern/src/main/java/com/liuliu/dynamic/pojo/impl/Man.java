package com.liuliu.dynamic.pojo.impl;

import com.liuliu.dynamic.pojo.Person;

/**
 * @Author: liulei
 * @Time: 2021/3/19 15:16
 * @Description
 */

public class Man implements Person {
    @Override
    public void walk() {
        System.out.println("man walk!");
    }

    @Override
    public void talk() {
        System.out.println("man talk!");
    }

    @Override
    public void sleep() {
        throw new RuntimeException("this guy have sleep problem");
    }
}
