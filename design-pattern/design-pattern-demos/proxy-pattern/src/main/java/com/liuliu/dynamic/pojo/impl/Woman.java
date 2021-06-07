package com.liuliu.dynamic.pojo.impl;

import com.liuliu.dynamic.pojo.Person;

/**
 * @Author: liulei
 * @Time: 2021/3/19 15:17
 * @Description
 */

public class Woman implements Person {
    @Override
    public void walk() {
        System.out.println("woman walk!");
    }

    @Override
    public void talk() {
        System.out.println("woman talk!");
    }

    @Override
    public void sleep() {
        System.out.println("woman sleep!");
    }
}
