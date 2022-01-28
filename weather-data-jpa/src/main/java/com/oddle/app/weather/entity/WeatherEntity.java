package com.oddle.app.weather.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    LocalDateTime date;
    // Temperature
    float temp;
    // Min temperature
    float tempMin;
    // Max temperature
    float tempMax;
    float pressure;
    float humidity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CityEntity.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cityId", referencedColumnName = "id", nullable = false)
    CityEntity city;
}
