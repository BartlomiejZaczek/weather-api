package com.example.weatherapi.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

public class DateValidator {
    public static boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            LocalDate currentDate = LocalDate.now();
            LocalDate lastDate = currentDate.plusDays(16);
            return
                    localDate.isAfter(currentDate.minusDays(1)) &&
                    localDate.isBefore(lastDate.plusDays(1));

        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
