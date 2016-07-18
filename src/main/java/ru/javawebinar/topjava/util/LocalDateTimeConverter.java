package ru.javawebinar.topjava.util;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime convert(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }
}
