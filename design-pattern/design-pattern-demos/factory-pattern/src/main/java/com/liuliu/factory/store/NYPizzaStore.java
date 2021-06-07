package com.liuliu.factory.store;

import com.liuliu.factory.material.NYMaterialFactory;
import com.liuliu.dynamic.pojo.pizza.NY.NYCheesePizza;
import com.liuliu.dynamic.pojo.pizza.NY.NYSaucePizza;
import com.liuliu.dynamic.pojo.pizza.NY.NYThinPizza;
import com.liuliu.dynamic.pojo.pizza.Pizza;

public class NYPizzaStore extends AbstractPizzaStore {
    @Override
    public Pizza createPizza(String type) {
        NYMaterialFactory nyMaterialFactory = new NYMaterialFactory();
        if ("cheese".equals(type)){
            return new NYCheesePizza(nyMaterialFactory);
        }else if ("sauce".equals(type)){
            return new NYSaucePizza(nyMaterialFactory);
        }else if ("thin".equals(type)){
            return new NYThinPizza(nyMaterialFactory);
        }
        return null;
    }
}
