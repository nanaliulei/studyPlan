package com.liuliu.impl;

public class Main {

    public static void main(String[] args) {
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(currentConditionsDisplay);
        weatherData.registerObserver(statisticsDisplay);
        weatherData.measurementsChanged();
        System.out.println("-----------------------移除观察者-----------------------------");
        weatherData.removeObserver(currentConditionsDisplay);
        weatherData.measurementsChanged();
    }
}
