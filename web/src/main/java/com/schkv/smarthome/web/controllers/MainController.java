package com.schkv.smarthome.web.controllers;


import com.schkv.smarthome.web.services.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
@Slf4j
public class MainController {

    private final DbService db;

    @Autowired
    public MainController(final DbService db) {
        this.db = db;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/watering_land")
    public String wateringLand(HttpServletRequest request) {
        request.setAttribute("value", "---");
        return "watering_land";
    }

    @GetMapping("/water_supply")
    public String waterSupply(HttpServletRequest request) {

        log.info("test");
        db.setWeather(LocalDate.now());
        return "water_supply";
    }
}
