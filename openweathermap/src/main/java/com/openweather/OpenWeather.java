package com.openweather;

import com.openweather.domain.OpenWeatherDTO;
import com.openweather.domain.OpenWeatherDayDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@EnableFeignClients
@CacheConfig(cacheNames = "openWeather")
public class OpenWeather {

    @Autowired
    private OpenWeatherService openWeatherService;

    @Cacheable
    public List<OpenWeatherDayDTO> get5DayForecast(final String city) {
        OpenWeatherDTO openWeatherDTO = openWeatherService.get5DayForecast(city);
        return Optional
                .ofNullable(openWeatherDTO.getOpenWeatherDayDTOList())
                .orElseGet(Collections::emptyList);
    }
}
