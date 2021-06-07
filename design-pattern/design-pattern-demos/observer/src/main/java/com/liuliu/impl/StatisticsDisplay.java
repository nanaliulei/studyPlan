package com.liuliu.impl;

import com.liuliu.Display;
import com.liuliu.Observer;

public class StatisticsDisplay implements Observer, Display {

    private int maxTemperature;
    private int minTemperature;

    public StatisticsDisplay() {
        this.maxTemperature = -100;
        this.minTemperature = 100;
    }

    @Override
    public void display() {
        System.out.println("统计数据展示板\n最高温度：" + maxTemperature + "\n最低温度：" + minTemperature +
                "\n平均温度：" + (maxTemperature + minTemperature) / 2);
    }

    @Override
    public void update(int temperature, double humidity, int pressure) {
        maxTemperature = Math.max(maxTemperature, temperature);
        minTemperature = Math.min(minTemperature, temperature);
        display();
    }
}
