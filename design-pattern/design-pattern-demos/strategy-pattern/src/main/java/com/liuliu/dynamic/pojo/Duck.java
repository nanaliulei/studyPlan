package com.liuliu.dynamic.pojo;

import com.liuliu.behavior.FlyBehavior;
import com.liuliu.behavior.QuackBehavior;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Duck implements IDuck{
    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public Duck() {
    }

    public void swim(){
        System.out.println("duck is swiming!");
    }

    public void display(){
        System.out.println("I'm a duck!");
    }

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBehavior.quack();
    }
}
