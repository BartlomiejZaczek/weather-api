package com.example.weatherapi.controller;

import com.example.weatherapi.model.WindsurfingLocation;
import com.example.weatherapi.service.WindsurfingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequiredArgsConstructor
public class WindsurfingController {

    private final WindsurfingService windsurfingService;

    @GetMapping("/api/location")
    public WindsurfingLocation getWindsurfingLocation(@RequestParam String date) throws FileNotFoundException {
        return windsurfingService.getWindsurfingLocation(date);
    }
}
