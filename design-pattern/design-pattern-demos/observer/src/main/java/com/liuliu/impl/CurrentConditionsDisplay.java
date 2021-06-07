package com.liuliu.impl;

import com.liuliu.Display;
import com.liuliu.Observer;

public class CurrentConditionsDisplay implements Observer, Display {

    private int temperature;
    private double humidity;
    private int pressure;

    @Override
    public void display() {
        System.out.println("当前情况展示板\n当前温度：" + temperature + "\n当前湿度：" + humidity + "\n当前气压：" + pressure);
    }

    @Override
    public void update(int temperature, double humidity, int pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
