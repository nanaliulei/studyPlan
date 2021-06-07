package com.liuliu.dynamic.pojo;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:48
 * @Description
 */

public class Duck implements Poultry{


    @Override
    public void cock() {
        System.out.println("嘎嘎！");
    }

    @Override
    public void fly() {
        System.out.println("fly five meters.");
    }
}
