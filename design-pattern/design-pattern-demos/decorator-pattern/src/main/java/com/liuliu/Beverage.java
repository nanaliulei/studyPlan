package com.liuliu;

public abstract class Beverage {

    String description = "unknow beverage";

    public String getDescription(){
        return description;
    }

    public abstract double cost();
}
