package com.oddle.app.weather.repository;

import com.oddle.app.weather.dto.CityModel;
import com.oddle.app.weather.dto.WeatherModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JpaWeatherRepositoryAdapter
 *
 * @author duy.huynh
 */
public interface JpaWeatherRepositoryAdapter {
    /**
     * Save weather data for retrieval
     *
     * @param weather
     */
    void saveWeather(WeatherModel weather, CityModel city);

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

    /**
     * Delete an existing weather record by id
     * @param id
     */
    void deleteHistoricalWeatherById(Long id);

    /**
     * Update an existing weather record
     * @param id
     * @param weather
     */
    void updateHistoricalWeather(Long id, WeatherModel weather);


}
