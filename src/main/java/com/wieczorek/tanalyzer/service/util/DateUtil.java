package com.wieczorek.tanalyzer.service.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDateTime toDate(String dateString) {
        return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public static boolean isBetween(LocalDateTime checkedDate, LocalDateTime fromDate, LocalDateTime toDate) {
        if (checkedDate.isEqual(fromDate) || checkedDate.isEqual(toDate)) {
            return true;
        }
        return checkedDate.isBefore(toDate) && checkedDate.isAfter(fromDate);
    }
}
