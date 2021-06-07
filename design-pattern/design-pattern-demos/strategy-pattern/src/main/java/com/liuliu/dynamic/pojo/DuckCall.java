package com.liuliu.dynamic.pojo;

public class DuckCall{
    private Duck duck;

    public DuckCall() {
        this.duck = new NormalDuck();
    }

    public static void main(String[] args) {
        DuckCall duckCall = new DuckCall();
        duckCall.display();
        duckCall.performFly();
        duckCall.performQuack();
    }

    public void swim() {
        System.out.println("I can't swim.");
    }

    public void display() {
        duck.display();
    }

    public void performFly() {
        System.out.println("I can't fly.");
    }

    public void performQuack() {
        duck.performQuack();
    }
}
