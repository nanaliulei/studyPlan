package com.liuliu.dynamic.pojo.pizza.Chicago;

import com.liuliu.factory.material.MaterialFactory;
import com.liuliu.dynamic.pojo.pizza.Pizza;

public class ChicagoCheesePizza extends Pizza {

    private MaterialFactory materialFactory;

    public ChicagoCheesePizza(MaterialFactory materialFactory) {
        this.materialFactory = materialFactory;
    }

    @Override
    public void prepare() {
        name = "Chicago cheese pizza";
        System.out.println("prepare " + name);
        dough = materialFactory.createDough();
        sauce = materialFactory.createSauce();
        cheese = materialFactory.createCheese();
    }
}
