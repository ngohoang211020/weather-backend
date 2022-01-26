package com.oddle.app.weather.mapper;

import com.oddle.app.weather.dto.WeatherDTO;
import com.oddle.app.weather.dto.WeatherModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiWeatherMapper {

    WeatherModel mapToWeatherDTO(WeatherDTO source);

}
