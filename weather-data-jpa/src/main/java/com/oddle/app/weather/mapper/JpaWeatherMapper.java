package com.oddle.app.weather.mapper;

import com.oddle.app.weather.entity.CityEntity;
import com.oddle.app.weather.entity.WeatherEntity;
import com.oddle.app.weather.dto.CityModel;
import com.oddle.app.weather.dto.WeatherModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JpaWeatherMapper {

    WeatherEntity mapToWeatherEntity(WeatherModel source);

    CityEntity mapToCityEntity(CityModel source);

    WeatherModel mapToWeatherModel(WeatherEntity source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", ignore = true)
    void updateWeatherEntity(@MappingTarget WeatherEntity toUpdate, WeatherModel source);
}
