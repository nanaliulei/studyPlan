package com.liuliu.template;

/**
 * @Author: liulei
 * @Time: 2021/3/5 10:33
 * @Description
 */

public abstract class DrinkTemplate implements Drink{

    public final void makeDrink(){
        boilWater();
        addMateiral();
        pourToCup();
        putCondiment();
    }

    protected abstract void addMateiral();

    private final void pourToCup() {
        System.out.println("pour the drink into cup!");
    }

    protected abstract void putCondiment();

    private void boilWater() {
        System.out.println("boil the water!");
    }
}
