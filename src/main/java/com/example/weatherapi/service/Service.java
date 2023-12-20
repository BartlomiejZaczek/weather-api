package com.example.weatherapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@org.springframework.stereotype.Service
public class Service {
    @Value("${api.key}")
    String API_KEY;
    String URL = "https://api.weatherbit.io/v2.0/forecast/daily?city=";
    private final RestTemplate restTemplate;

    public Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /** Retrieve location data from URL and return as ResponseEntity of Map type */
    public Map<String, Object> getLocationWeatherDetails(String city) {
        ResponseEntity<Map> response = restTemplate.getForEntity(
                URL + city + "&key=" + API_KEY,
                Map.class);
        return response.getBody();
    }
}
