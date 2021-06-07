package com.liuliu.dynamic.pojo.pizza.Chicago;

import com.liuliu.factory.material.MaterialFactory;
import com.liuliu.dynamic.pojo.pizza.Pizza;

public class ChicagoThickPizza extends Pizza {

    private MaterialFactory materialFactory;

    public ChicagoThickPizza(MaterialFactory materialFactory) {
        this.materialFactory = materialFactory;
    }

    @Override
    public void prepare() {
        name = "Chicago thick pizza";
        System.out.println("prepare " + name);
        dough = materialFactory.createDough();
        sauce = materialFactory.createSauce();
        cheese = materialFactory.createCheese();
    }
}
