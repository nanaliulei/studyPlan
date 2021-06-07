package com.liuliu.factory.store;

import com.liuliu.dynamic.pojo.pizza.Pizza;


public abstract class AbstractPizzaStore {

    public Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.split();
        pizza.bale();
        return pizza;
    }

    public abstract Pizza createPizza(String type);
}
