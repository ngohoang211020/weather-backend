package com.oddle.app.weather.adapter;

import com.oddle.app.weather.entity.CityEntity;
import com.oddle.app.weather.entity.WeatherEntity;
import com.oddle.app.weather.mapper.JpaWeatherMapper;
import com.oddle.app.weather.dto.CityModel;
import com.oddle.app.weather.dto.WeatherModel;
import com.oddle.app.weather.repository.JpaCityRepository;
import com.oddle.app.weather.repository.JpaWeatherRepository;
import com.oddle.app.weather.repository.JpaWeatherRepositoryAdapter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JpaWeatherServiceAdapter
 * The service response for interaction oddle's database
 *
 * @author duy.huynh
 */
@Service
public class JpaWeatherRepositoryAdapterImpl implements JpaWeatherRepositoryAdapter {
    private final JpaWeatherRepository jpaWeatherRepository;
    private final JpaCityRepository jpaCityRepository;
    private final JpaWeatherMapper jpaWeatherMapper;

    public JpaWeatherRepositoryAdapterImpl(JpaWeatherRepository jpaWeatherRepository, JpaCityRepository jpaCityRepository, JpaWeatherMapper jpaWeatherMapper) {
        this.jpaWeatherRepository = jpaWeatherRepository;
        this.jpaCityRepository = jpaCityRepository;
        this.jpaWeatherMapper = jpaWeatherMapper;
    }

    /**
     * Save weather data for retrieval
     *
     * @param weather
     */
    @Override
    public void saveWeather(WeatherModel weather, CityModel city) {
        WeatherEntity weatherEntity = jpaWeatherMapper.mapToWeatherEntity(weather);
        CityEntity cityEntity = jpaCityRepository.findByCountryCodeAndName(city.getCountryCode(), city.getName());
        if (cityEntity != null) {
            weatherEntity.setCity(cityEntity);
        } else {
            weatherEntity.setCity(jpaWeatherMapper.mapToCityEntity(city));
        }
        jpaWeatherRepository.save(weatherEntity);
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
        return jpaWeatherRepository.findByDateBetween(from, to).stream().map(jpaWeatherMapper::mapToWeatherModel).collect(Collectors.toList());
    }

    /**
     * Delete an existing weather record
     *
     * @param from
     * @param to
     */
    @Override
    @Transactional
    public void deleteHistoricalWeather(LocalDateTime from, LocalDateTime to) {
        jpaWeatherRepository.deleteAllByDateBetween(from, to);
    }

    @Override
    @Transactional
    public void deleteHistoricalWeatherById(Long id) {
        jpaWeatherRepository.deleteById(id);
    }

    /**
     * Update an existing weather record
     *
     * @param weather
     */
    @Override
    @Transactional
    public void updateHistoricalWeather(Long id, WeatherModel weather) {
        jpaWeatherMapper.updateWeatherEntity(jpaWeatherRepository.findById(id).get(), weather);
    }
}
