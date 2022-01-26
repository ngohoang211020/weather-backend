package com.oddle.app.weather.mapper;

import com.oddle.app.weather.dto.WeatherDTO;
import com.oddle.app.weather.dto.WeatherModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApiWeatherMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    WeatherModel mapToWeatherDTO(WeatherDTO source);

}
