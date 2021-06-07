package com.liuliu.dynamic.pojo;

/**
 * @Author: liulei
 * @Time: 2021/3/4 15:05
 * @Description
 */

public class Door {

    private String name;

    public Door(String name) {
        this.name = name;
    }

    public void open(){
        System.out.println("open the " + name + "!");
    }

    public void close(){
        System.out.println("close the " + name + "!");
    }
}
