package com.liuliu.dynamic.pojo.pizza.NY;

import com.liuliu.factory.material.MaterialFactory;
import com.liuliu.dynamic.pojo.pizza.Pizza;

public class NYThinPizza extends Pizza {

    private MaterialFactory materialFactory;

    public NYThinPizza(MaterialFactory materialFactory) {
        this.materialFactory = materialFactory;
    }

    @Override
    public void prepare() {
        name = "new York thin pizza";
        System.out.println("prepare " + name);
        dough = materialFactory.createDough();
        sauce = materialFactory.createSauce();
        cheese = materialFactory.createCheese();
    }
}
