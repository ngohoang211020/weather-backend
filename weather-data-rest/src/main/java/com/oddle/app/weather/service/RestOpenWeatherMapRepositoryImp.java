package com.oddle.app.weather.service;

import com.oddle.app.weather.mapper.WeatherMapper;
import com.oddle.app.weather.dto.CurrentWeatherDTO;
import com.oddle.app.weather.dto.CurrentWeatherModel;
import com.oddle.app.weather.repository.RestOpenWeatherMapRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The service response for consuming RESTFull API from openweathermap.org
 *
 * @author duy.huynh
 */
@Service
public class RestOpenWeatherMapRepositoryImp implements RestOpenWeatherMapRepository {

    @Value("${weather.map.current.resource.url}")
    private String openWeatherMapCurrentUrl;

    @Value("${weather.map.appid}")
    private String weatherMapAppId;

    private final WeatherMapper weatherMapper;
    private final RestTemplate restTemplate;

    public RestOpenWeatherMapRepositoryImp(RestTemplate restTemplate, WeatherMapper weatherMapper) {
        this.restTemplate = restTemplate;
        this.weatherMapper = weatherMapper;
    }

    /**
     * Search for today weather of a specific city
     *
     * @param city
     * @return CurrentWeather
     */
    @Override
    public CurrentWeatherModel searchWeatherToday(String city) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openWeatherMapCurrentUrl)
                .queryParam("q", city)
                .queryParam("apiKey", weatherMapAppId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<CurrentWeatherDTO> weather = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, entity, CurrentWeatherDTO.class);
        return weatherMapper.mapToCurrentWeatherModel(weather.getBody());
    }
}
