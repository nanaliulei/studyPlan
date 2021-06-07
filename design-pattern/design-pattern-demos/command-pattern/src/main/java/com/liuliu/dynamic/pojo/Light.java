package com.liuliu.dynamic.pojo;

/**
 * @Author: liulei
 * @Time: 2021/3/4 14:48
 * @Description
 */

public class Light {
    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on(){
        System.out.println("turn on " + name + "!");
    }

    public void off(){
        System.out.println("turn off " + name + "!");
    }
}
