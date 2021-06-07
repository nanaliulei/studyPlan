package com.liuliu.dynamic.pojo.cheese;

public class FreshCheese implements Cheese {
    @Override
    public String description() {
        return "fresh cheese";
    }

    @Override
    public String toString() {
        return description();
    }
}
