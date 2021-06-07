package com.liuliu.dynamic.pojo.cheese;

public class SoftCheese implements Cheese {
    @Override
    public String description() {
        return "soft cheese";
    }

    @Override
    public String toString() {
        return description();
    }
}
