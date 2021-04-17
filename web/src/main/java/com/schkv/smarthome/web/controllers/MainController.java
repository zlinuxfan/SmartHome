package com.schkv.smarthome.web.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class MainController {

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
        return "water_supply";
    }
}
