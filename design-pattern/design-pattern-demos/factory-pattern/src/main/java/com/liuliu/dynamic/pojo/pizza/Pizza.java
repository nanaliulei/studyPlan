package com.liuliu.dynamic.pojo.pizza;

import com.liuliu.dynamic.pojo.cheese.Cheese;
import com.liuliu.dynamic.pojo.dough.Dough;
import com.liuliu.dynamic.pojo.sauce.Sauce;

public abstract class Pizza {
    protected String name;
    protected Dough dough;
    protected Sauce sauce;
    protected Cheese cheese;

    public abstract void prepare();

    public void bake(){
        System.out.println("bake for 20 minutes!");
    }

    public void split(){
        System.out.println("split to pieces!");
    }

    public void bale(){
        System.out.println("bale the pizza!");
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "dough=" + dough +
                ", sauce=" + sauce +
                ", cheese=" + cheese +
                '}';
    }
}
