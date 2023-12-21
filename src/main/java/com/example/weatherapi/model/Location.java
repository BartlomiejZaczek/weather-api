package com.example.weatherapi.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

@Getter
public class Location {
    @CsvBindByPosition(position = 0)
    private Double latitude;

    @CsvBindByPosition(position = 1)
    private Double longitude;

    @CsvBindByPosition(position = 2)
    private String name;
}
