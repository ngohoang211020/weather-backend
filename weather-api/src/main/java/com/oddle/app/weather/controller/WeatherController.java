package com.oddle.app.weather.controller;

import com.oddle.app.weather.dto.WeatherDTO;
import com.oddle.app.weather.mapper.ApiWeatherMapper;
import com.oddle.app.weather.dto.CurrentWeatherModel;
import com.oddle.app.weather.dto.WeatherModel;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Expose useful functions
 * Adapter for handler the requests from client
 *
 * @author duy.huynh
 */
@CrossOrigin(origins = "*")
@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final ApiWeatherMapper apiWeatherMapper;

    public WeatherController(WeatherService weatherService, ApiWeatherMapper apiWeatherMapper) {
        this.weatherService = weatherService;
        this.apiWeatherMapper = apiWeatherMapper;
    }

    @GetMapping(value = "/api/weather")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CurrentWeatherModel> getWeathers(@RequestParam(required = false) String city) {
        return new ResponseEntity<>(weatherService.searchWeatherToday(city), HttpStatus.OK);
    }

    @PostMapping(value = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrentWeatherModel> createWeather(@RequestBody(required = true) String city) {
        return new ResponseEntity<>(weatherService.saveWeather(city), HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/weather/getHistoricalWeather")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<WeatherModel>> getHistoricalWeather(@RequestParam(required = true) String from, @RequestParam(required = true) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return new ResponseEntity<>(weatherService.getHistoricalWeather(LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter)), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/weather/deleteHistoricalWeather")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteHistoricalWeather(@RequestParam(required = true) String from, @RequestParam(required = true) String to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        weatherService.deleteHistoricalWeather(LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/weather/deleteHistoricalWeather/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteHistoricalWeather(@PathVariable("id") Long id) {
        weatherService.deleteHistoricalWeatherById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/api/weather/updateHistoricalWeather")
    public ResponseEntity<?> updateHistoricalWeather(@RequestBody(required = true) WeatherDTO weatherDTO, @RequestParam(required = true) Long id) {
        weatherService.updateHistoricalWeather(id, apiWeatherMapper.mapToWeatherDTO(weatherDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/api/weather/currentLocation")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CurrentWeatherModel> getWeathersAtYourLocation(@RequestParam(required = false) Float lat
            ,@RequestParam(required = false) Float lon) {
        return new ResponseEntity<>(weatherService.searchWeatherAtYourLocation(lat,lon), HttpStatus.OK);
    }
}
