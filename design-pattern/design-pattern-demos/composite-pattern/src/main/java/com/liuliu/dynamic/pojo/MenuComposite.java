package com.liuliu.dynamic.pojo;

import java.util.List;

/**
 * @Author: liulei
 * @Time: 2021/3/15 10:44
 * @Description
 */

public class MenuComposite implements Composite {

    String name;
    String vegetarian;

    public MenuComposite(String name, String vegetarian) {
        this.name = name;
        this.vegetarian = vegetarian;
    }

    @Override
    public List<Composite> getMenuItems() {
        throw new RuntimeException("I's a menu item!");
    }

    @Override
    public void display() {
        System.out.println("menu item: " + name + " " + vegetarian);
    }
}
