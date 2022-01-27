package com.oddle.app.weather.service;

import com.oddle.app.weather.dto.CurrentWeatherModel;
import com.oddle.app.weather.dto.WeatherModel;
import com.oddle.app.weather.repository.JpaWeatherRepositoryAdapter;
import com.oddle.app.weather.repository.RestOpenWeatherMapRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The service response for search for today's weather,  save weather data for retrieval,
 * get historical weather data, etc
 *
 * @author duy.huynh
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    private final RestOpenWeatherMapRepository restWeatherService;

    private final JpaWeatherRepositoryAdapter jpaWeatherRepositoryAdapter;

    public WeatherServiceImpl(RestOpenWeatherMapRepository restWeatherService, JpaWeatherRepositoryAdapter jpaWeatherRepositoryAdapter) {
        this.restWeatherService = restWeatherService;
        this.jpaWeatherRepositoryAdapter = jpaWeatherRepositoryAdapter;
    }

    /**
     * Search for today weather of a specific city
     *
     * @param city
     * @return CurrentWeather
     */
    @Override
    public CurrentWeatherModel searchWeatherToday(String city) {
        return restWeatherService.searchWeatherToday(city);
    }

    /**
     * Save weather data for retrieval
     *
     * @param city
     */
    @Override
    public CurrentWeatherModel saveWeather(String city) {
        CurrentWeatherModel currentWeather = restWeatherService.searchWeatherToday(city);
        jpaWeatherRepositoryAdapter.saveWeather(currentWeather.getWeather(), currentWeather.getCity());
        currentWeather.getCity();
        return currentWeather;
    }

    /**
     * Look for weather data from past periods
     *
     * @param from
     * @param to
     * @return List<WeatherModel>
     */
    @Override
    public List<WeatherModel> getHistoricalWeather(LocalDateTime from, LocalDateTime to) {
        return jpaWeatherRepositoryAdapter.getHistoricalWeather(from, to);
    }

    /**
     * Delete an existing weather record
     *
     * @param from
     * @param to
     */
    @Override
    public void deleteHistoricalWeather(LocalDateTime from, LocalDateTime to) {
        jpaWeatherRepositoryAdapter.deleteHistoricalWeather(from, to);
    }

    /**
     * Delete an existing weather record by id
     *
     * @param id
     */
    @Override
    public void deleteHistoricalWeatherById(Long id) {
        jpaWeatherRepositoryAdapter.deleteHistoricalWeatherById(id);
    }

    /**
     * Update an existing weather record
     *
     * @param id
     * @param weather
     */
    @Override
    public void updateHistoricalWeather(Long id, WeatherModel weather) {
        jpaWeatherRepositoryAdapter.updateHistoricalWeather(id, weather);
    }

    @Override
    public CurrentWeatherModel searchWeatherAtYourLocation(Float lat, Float lon) {
        return restWeatherService.searchWeatherAtYourLocation(lat,lon);
    }
}
