package com.example.weatherapi.service;

import com.example.weatherapi.model.Location;
import com.example.weatherapi.model.WindsurfingLocation;
import com.example.weatherapi.utils.DateValidator;
import com.example.weatherapi.utils.InvalidDateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class WindsurfingService {
    @Value("${api.key}")
    private String API_KEY;
    private final String URL = "https://api.weatherbit.io/v2.0/forecast/daily?";
    private final RestTemplate restTemplate;
    private final LocationService locationService;

    public WindsurfingService(RestTemplate restTemplate, LocationService locationService) {
        this.restTemplate = restTemplate;
        this.locationService = locationService;
    }
    /**
     * Retrieve location data from URL and return as ResponseEntity of Map type
     */
    protected Map<String, Object> getLocationWeatherDetails(String city, Double latitude, Double longitude) {
        String location = "";
        if (city != null) {
            location = ("city=" + city + "&");
        }
        ResponseEntity<Map> response = restTemplate.getForEntity(
                URL + location + "lat=" + latitude + "&lon=" + longitude + "&key=" + API_KEY,
                Map.class);
        return response.getBody();
    }
    /**
     * Choose and return best suiting location for windsurfing
     */
    public WindsurfingLocation getWindsurfingLocation(String date) throws FileNotFoundException {
        if (!DateValidator.isValidDate(date)) {
            throw new InvalidDateException("Invalid date");
        }
        // List all locations
        List<Location> locationList = locationService.listLocations();
        // Retrieve weather details for specific location
        return locationList.stream()
                .flatMap(location -> {
                    Map<String, Object> weatherDetails = getLocationWeatherDetails(
                            location.getName(),
                            location.getLatitude(),
                            location.getLongitude());
                    if (weatherDetails != null) {
                        List<Map<String, Object>> data = (List<Map<String, Object>>) weatherDetails.get("data");
                        // Check selection criteria
                        return data.stream()
                                .filter(details -> date.equals(details.get("valid_date")))
                                .filter(details -> {
                                    double temp = ((Number) details.get("temp")).doubleValue();
                                    double wind_spd = ((Number) details.get("wind_spd")).doubleValue();
                                    return !(temp < 5 || temp > 35 || wind_spd < 5 || wind_spd > 18);
                                })
                                .map(details -> {
                                    double temp = ((Number) details.get("temp")).doubleValue();
                                    double wind_spd = ((Number) details.get("wind_spd")).doubleValue();
                                    String cityName = weatherDetails.get("city_name").toString();
                                    return WindsurfingLocation.builder()
                                            .name(cityName)
                                            .temperature(temp)
                                            .windSpeed(wind_spd)
                                            .build();
                                });
                    }
                    return Stream.empty();
                })
                // Choose the best location
                .max(Comparator.comparingDouble(location -> location.getWindSpeed() * 3 + location.getTemperature()))
                .orElse(WindsurfingLocation.builder().build());
    }
}
