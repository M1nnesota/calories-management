package ru.javawebinar.topjava.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter {
    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime convert(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }
}
