package com.openweather.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.openweather.OpenWeatherProfile;

/**
 * OpenWeatherDayDTO with flatten values trick, no Lombock here :(
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public final class OpenWeatherDayDTO {

    @JsonProperty("dt")
    private long dt;

    private OpenWeatherValuesDTO values;

    @JsonProperty("main")
    public void setValues(final OpenWeatherValuesDTO values) {
        this.values = values;
    }

    @JsonIgnore
    public OpenWeatherValuesDTO getValues() {
        return this.values;
    }

    @JsonView({
            OpenWeatherProfile.Temperature.class,
            OpenWeatherProfile.Pressure.class,
            OpenWeatherProfile.Humidity.class})
    public long getDt() {
        return dt;
    }

    @JsonView(OpenWeatherProfile.Temperature.class)
    public double getTemp() {
        return values.getTemp();
    }

    @JsonView(OpenWeatherProfile.Pressure.class)
    public double getPressure() {
        return values.getPressure();
    }

    @JsonView(OpenWeatherProfile.Humidity.class)
    public int getHumidity() {
        return values.getHumidity();
    }

}
