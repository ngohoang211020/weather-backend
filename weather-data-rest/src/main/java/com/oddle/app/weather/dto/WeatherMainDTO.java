package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is presentation the observations from the current weather.
 * Such as: wind speed, dewpoint, visibility and atmospheric pressure measurements as well as a detailed temperature, etc.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeatherMainDTO {
    private double temp;
    @JsonProperty("feels_like")
    private double feelsLike;
    @JsonProperty("temp_min")
    private double tempMin;
    @JsonProperty("temp_max")
    private double tempMax;
    private int pressure;
    private int humidity;
}
