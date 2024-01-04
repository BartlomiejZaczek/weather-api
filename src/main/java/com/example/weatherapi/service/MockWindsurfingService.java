package com.example.weatherapi.service;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Primary
@Profile("dev")
public class MockWindsurfingService extends WindsurfingService {

    public MockWindsurfingService(RestTemplate restTemplate, LocationService locationService) {
        super(restTemplate, locationService);
    }
    @Override
    protected Map<String, Object> getLocationWeatherDetails(String city, Double latitude, Double longitude) {
        Map<String, Object> mockResponse = new HashMap<>();
        switch (city) {
            case "Jastarnia":
                mockResponse.put("data", mockWeatherData(35,18));
                mockResponse.put("city_name", "Jastarnia");
                mockResponse.put("lon", "1.0");
                mockResponse.put("lat", "1.0");
                break;
            case "Bridgetown":
                mockResponse.put("data", mockWeatherData(0,10));
                mockResponse.put("city_name", "Bridgetown");
                mockResponse.put("lon", "2.0");
                mockResponse.put("lat", "2.0");
            case "Fortaleza":
                mockResponse.put("data", mockWeatherData(5,40));
                mockResponse.put("city_name", "Fortaleza");
                mockResponse.put("lon", "3.0");
                mockResponse.put("lat", "3.0");
            case "Pissouri":
                mockResponse.put("data", mockWeatherData(8,10));
                mockResponse.put("city_name", "Pissouri");
                mockResponse.put("lon", "4.0");
                mockResponse.put("lat", "4.0");
            case "Le Morne":
                mockResponse.put("data", mockWeatherData(100,100));
                mockResponse.put("city_name", "Le Morne");
                mockResponse.put("lon", "5.0");
                mockResponse.put("lat", "5.0");
            default:
                break;
            }
        return mockResponse;
    }
    private List<Map<String, Object>> mockWeatherData(double temperature, double windSpeed) {
        Map<String, Object> weatherData = new HashMap<>();
        weatherData.put("valid_date", "2024-01-10");
        weatherData.put("temp", temperature);
        weatherData.put("wind_spd", windSpeed);
        return Collections.singletonList(weatherData);
    }
}
