package com.example.weatherapi.service;

import com.example.weatherapi.model.Location;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class LocationService {

    /** Return location List from locations.csv */
    public List<Location> listLocations() throws FileNotFoundException {
        return new CsvToBeanBuilder<Location>(new FileReader("src/main/resources/locations.csv"))
                .withType(Location.class)
                .build()
                .parse();
    }
}
