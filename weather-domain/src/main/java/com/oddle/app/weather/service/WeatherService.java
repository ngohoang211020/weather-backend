package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.CurrentWeatherModel;
import com.oddle.app.weather.dto.WeatherModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The service response for search for today's weather,  save weather data for retrieval,
 * get historical weather data, etc
 *
 * @author duy.huynh
 */
public interface WeatherService {
    /**
     * Search for today weather of a specific city
     *
     * @param city
     * @return CurrentWeather
     */
    CurrentWeatherModel searchWeatherToday(String city);

    /**
     * Save weather data for retrieval
     *
     * @param city
     */
    CurrentWeatherModel saveWeather(String city);

    /**
     * Look for weather data from past periods
     *
     * @param from
     * @param to
     * @return List<WeatherModel>
     */
    List<WeatherModel> getHistoricalWeather(LocalDateTime from, LocalDateTime to);

    /**
     * Delete an existing weather record
     *
     * @param from
     * @param to
     */
    void deleteHistoricalWeather(LocalDateTime from, LocalDateTime to);

    void deleteHistoricalWeatherById(Long id);
    /**
     * Update an existing weather record
     *
     * @param id
     * @param weather
     */
    void updateHistoricalWeather(Long id, WeatherModel weather);
}
