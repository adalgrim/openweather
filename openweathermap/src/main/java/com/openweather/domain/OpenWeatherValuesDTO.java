package com.openweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class OpenWeatherValuesDTO {
    private double temp;
    private double pressure;
    private int humidity;
}

