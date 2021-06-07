package com.liuliu.observer;

import com.liuliu.Display;
import com.liuliu.observerable.WeatherData;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Display, Observer {
    private Observable  observable;
    private float temperature;
    private float humidity;

    public ForecastDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("天气预报展示板\n预计明天温度：" + temperature * 1.5 + "\n预计明天湿度：" + humidity * 1.5 + "\n");
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
