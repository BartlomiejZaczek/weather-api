package com.example.weatherapi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@org.springframework.stereotype.Service
public class Service {
    @Value("${api.key}")
    String API_KEY;
    String URL = "https://api.weatherbit.io/v2.0/forecast/daily?city=";
    private final RestTemplate restTemplate;

    public Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
