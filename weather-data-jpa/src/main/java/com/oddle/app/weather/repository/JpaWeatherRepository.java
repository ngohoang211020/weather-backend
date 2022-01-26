package com.oddle.app.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oddle.app.weather.entity.WeatherEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaWeatherRepository extends JpaRepository<WeatherEntity, Long> {
    List<WeatherEntity> findByDateBetween(LocalDateTime from, LocalDateTime to);

    void deleteAllByDateBetween(LocalDateTime from, LocalDateTime to);
}
