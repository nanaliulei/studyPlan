package com.liuliu.dynamic.pojo;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:51
 * @Description
 */

public class Chicken implements Poultry {
    @Override
    public void cock() {
        System.out.println("gugugen!");
    }

    @Override
    public void fly() {
        System.out.println("fly one meter!");
    }
}
