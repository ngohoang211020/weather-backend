package com.oddle.app.weather.repository;

import com.oddle.app.weather.dto.CurrentWeatherModel;

/**
 * The service response for consuming RESTFull API from openweathermap.org
 *
 * @author duy.huynh
 */
public interface RestOpenWeatherMapRepository {

    /**
     * Search for today weather of a specific city
     *
     * @param city
     * @return CurrentWeather
     */
    CurrentWeatherModel searchWeatherToday(String city);

    CurrentWeatherModel searchWeatherAtYourLocation(Float lat, Float lon);
}
