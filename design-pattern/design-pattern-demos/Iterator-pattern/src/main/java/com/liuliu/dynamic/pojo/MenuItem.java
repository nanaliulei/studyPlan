package com.liuliu.dynamic.pojo;

/**
 * @Author: liulei
 * @Time: 2021/3/8 14:14
 * @Description
 */

public class MenuItem {
    private String name;
    private float price;

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public MenuItem(String name, float price) {
        this.name = name;
        this.price = price;
    }
}
