package com.liuliu;

public class Coffee extends Beverage {

    public Coffee() {
        description = "coffee";
    }

    @Override
    public double cost() {
        return 10;
    }
}
