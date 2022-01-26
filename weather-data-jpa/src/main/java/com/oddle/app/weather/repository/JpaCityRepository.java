package com.oddle.app.weather.repository;


import com.oddle.app.weather.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCityRepository extends JpaRepository<CityEntity, Long> {
    CityEntity findByCountryCodeAndName(String countyCode, String name);
}
