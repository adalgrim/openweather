package com.openweather;

import com.openweather.domain.OpenWeatherDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "OpenWeather",
        url = "${service.openweather.url}",
        configuration = OpenWeatherFeignConfig.class)
interface OpenWeatherService {

    @GetMapping(consumes = "",
            path = "/data/2.5/forecast"
                    + "?APPID=${service.openweather.apiKey}"
                    + "&mode=${service.openweather.mode}"
                    + "&units=${service.openweather.units}")
    OpenWeatherDTO get5DayForecast(@RequestParam(value = "q") String city);

}
