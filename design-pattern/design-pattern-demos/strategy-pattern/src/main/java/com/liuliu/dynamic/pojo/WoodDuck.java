package com.liuliu.dynamic.pojo;

import com.liuliu.behavior.impl.NoFlyBehavior;
import com.liuliu.behavior.impl.Squeak;

public class WoodDuck extends Duck {
    public WoodDuck() {
        setFlyBehavior(new NoFlyBehavior());
        setQuackBehavior(new Squeak());
    }

    public static void main(String[] args) {
        WoodDuck woodDuck = new WoodDuck();
        woodDuck.display();
        woodDuck.swim();
        woodDuck.performFly();
        woodDuck.performQuack();
    }
}
