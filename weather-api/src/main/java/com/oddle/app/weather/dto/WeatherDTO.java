package com.oddle.app.weather.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherDTO {

    LocalDateTime date;
    // Temperature
    float temp;
    // Min temperature
    float tempMin;
    // Max temperature
    float tempMax;
    float pressure;
    float humidity;
}
