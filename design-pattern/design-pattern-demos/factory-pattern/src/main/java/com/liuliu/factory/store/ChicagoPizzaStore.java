package com.liuliu.factory.store;

import com.liuliu.factory.material.ChicagoMaterialFactory;
import com.liuliu.dynamic.pojo.pizza.Chicago.ChicagoCheesePizza;
import com.liuliu.dynamic.pojo.pizza.Chicago.ChicagoSaucePizza;
import com.liuliu.dynamic.pojo.pizza.Chicago.ChicagoThickPizza;
import com.liuliu.dynamic.pojo.pizza.Pizza;

public class ChicagoPizzaStore extends AbstractPizzaStore {
    @Override
    public Pizza createPizza(String type) {
        ChicagoMaterialFactory chicagoMaterialFactory = new ChicagoMaterialFactory();
        if ("cheese".equals(type)){
            return new ChicagoCheesePizza(chicagoMaterialFactory);
        }else if ("sauce".equals(type)){
            return new ChicagoSaucePizza(chicagoMaterialFactory);
        }else if ("thick".equals(type)){
            return new ChicagoThickPizza(chicagoMaterialFactory);
        }
        return null;
    }
}
