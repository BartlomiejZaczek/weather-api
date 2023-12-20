package com.example.weatherapi.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WindsurfingLocation {
    private String name;
    private Double temp;
    private Double wind_speed;
}
