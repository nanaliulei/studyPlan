package com.liuliu;

import com.liuliu.observer.CurrentConditionsDisplay;
import com.liuliu.observer.ForecastDisplay;
import com.liuliu.observerable.WeatherData;

public class Main {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.setMeasurements(1, 0.3F, 1231);
        System.out.println("-----------delete forecast--------------");
        weatherData.deleteObserver(forecastDisplay);
        weatherData.setMeasurements(2, 0.5F, 1234);
    }
}
