package com.liuliu.impl;

import com.liuliu.Observer;
import com.liuliu.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherData implements Subject {

    static List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(getTemperature(), getHumidity(), getPressure());
        }
    }

    private int getTemperature(){
        return new Random().nextInt(40);
    }

    private double getHumidity(){
        return new Random().nextDouble();
    }

    private int getPressure(){
        return new Random().nextInt(1000);
    }

    public void measurementsChanged(){
        notifyObserver();
    }
}
