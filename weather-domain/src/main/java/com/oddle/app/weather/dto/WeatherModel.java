package com.oddle.app.weather.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherModel {
    Long id;
    LocalDateTime date;
    // Temperature
    float temp;
    // Min temperature
    float tempMin;
    // Max temperature
    float tempMax;
    float pressure;
    float humidity;
    CityModel city;
}
