package com.openweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public final class OpenWeatherDTO {

    @JsonProperty("list")
    private List<OpenWeatherDayDTO> openWeatherDayDTOList;

}
