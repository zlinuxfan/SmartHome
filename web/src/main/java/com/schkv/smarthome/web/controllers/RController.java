package com.schkv.smarthome.web.controllers;

import com.schkv.smarthome.web.servises.MqttService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@RestController
@Slf4j
public class RController {
    BlockingQueue<String> values = new ArrayBlockingQueue<>(10);
    MqttService subscriber;
    String lastValue = "---";

//    public RController() throws MqttException {
//        subscriber = new MqttService(
//                "192.168.31.77",
//                1883,
//                "home/watering/wetGroundSensor/value",
//                values
//        );
//    }

    @GetMapping("/home/watering/wetGroundSensor/value")
    public ResponseEntity<String> homeWateringWetGroundSensorValue(HttpServletRequest request) {
        try {
            if (! values.isEmpty()) {
                this.lastValue = values.take();
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }

        return new ResponseEntity<>(lastValue, HttpStatus.OK);
    }
}
