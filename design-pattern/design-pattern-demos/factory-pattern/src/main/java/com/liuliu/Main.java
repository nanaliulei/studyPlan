package com.liuliu;

import com.liuliu.factory.store.ChicagoPizzaStore;
import com.liuliu.factory.store.NYPizzaStore;
import com.liuliu.dynamic.pojo.pizza.Pizza;

public class Main {

    public static void main(String[] args) {
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        Pizza cheesePizza = nyPizzaStore.orderPizza("cheese");
        System.out.println(cheesePizza);
        ChicagoPizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
        Pizza caCheesePizza = chicagoPizzaStore.orderPizza("cheese");
        System.out.println(caCheesePizza);
    }
}
