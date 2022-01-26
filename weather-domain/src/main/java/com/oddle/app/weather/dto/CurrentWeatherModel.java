package com.oddle.app.weather.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrentWeatherModel {
    CityModel city;
    WeatherModel weather;
    List<WeatherDescModel> weatherDescModels;

}
