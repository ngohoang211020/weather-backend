package com.oddle.app.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrentWeatherDTO {
    @JsonProperty("coord")
    GeoDTO geo;
    List<WeatherDescDTO> weather;
    @JsonProperty("main")
    WeatherMainDTO weatherMain;
    WindDTO wind;
    CloudDTO clouds;
    SysDTO sys;
    long timezone;
    @JsonProperty("dt")
    long date;
    @JsonProperty("name")
    String cityName;
}
