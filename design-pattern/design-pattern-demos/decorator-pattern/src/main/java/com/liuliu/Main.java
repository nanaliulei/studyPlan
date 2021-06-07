package com.liuliu;

import com.liuliu.decorator.Whip;
import com.liuliu.decorator.Mocha;

public class Main {
    public static void main(String[] args) {
        Beverage coffee = new Coffee();
        coffee = new Mocha(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.cost());
        coffee = new Whip(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.cost());
        coffee = new Mocha(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.cost());
    }
}
