package com.example.weather.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.openweather.OpenWeather;
import com.openweather.OpenWeatherProfile;
import com.openweather.domain.OpenWeatherDayDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/weather/")
public class WeatherController {

    @Autowired
    private OpenWeather service;

    @JsonView(OpenWeatherProfile.Temperature.class)
    @GetMapping("/temperature")
    @ApiOperation(value = "View temperature forecast for 5 days", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Successfully retrieved list"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND,
                    message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<OpenWeatherDayDTO>> getTemperature(@RequestParam("city") final String city) {
        return getWeatherData(city);
    }

    @JsonView(OpenWeatherProfile.Pressure.class)
    @GetMapping("/pressure")
    @ApiOperation(value = "View pressure forecast for 5 days", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Successfully retrieved list"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND,
                    message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<OpenWeatherDayDTO>> getPressure(@RequestParam("city") final String city) {
        return getWeatherData(city);
    }

    @JsonView(OpenWeatherProfile.Humidity.class)
    @GetMapping("/humidity")
    @ApiOperation(value = "View humidity forecast for 5 days", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Successfully retrieved list"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND,
                    message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<OpenWeatherDayDTO>> getHumidity(@RequestParam("city") final String city) {
        return getWeatherData(city);
    }

    private ResponseEntity<List<OpenWeatherDayDTO>> getWeatherData(String city) {
        List<OpenWeatherDayDTO> openWeatherDayList = service.get5DayForecast(city);
        return (!openWeatherDayList.isEmpty())
                ? new ResponseEntity<>(openWeatherDayList, HttpStatus.OK.value())
                : new ResponseEntity<>(openWeatherDayList, HttpStatus.NOT_FOUND);

    }
}