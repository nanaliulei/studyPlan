package com.liuliu.dynamic.pojo;

import com.liuliu.behavior.impl.NormalFlyBehavoir;
import com.liuliu.behavior.impl.Quack;

public class NormalDuck extends Duck {
    public NormalDuck() {
        setFlyBehavior(new NormalFlyBehavoir());
        setQuackBehavior(new Quack());
    }

    public static void main(String[] args) {
        NormalDuck normalDuck = new NormalDuck();
        normalDuck.display();
        normalDuck.swim();
        normalDuck.performFly();
        normalDuck.performQuack();
    }
}
