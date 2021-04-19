package com.schkv.smarthome.web.services;

import java.time.LocalDate;
import com.schkv.smarthome.web.models.Weather;

public interface DbService {
    public Weather getCurrentWeather();

    public void setWeather(LocalDate currentDate);
}
