package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T lt, T from, T to) {
        return lt.compareTo(from) >= 0 && lt.compareTo(to) < 0;
    }

    public static <T extends Comparable<T>> boolean isBetween(T lt, T from, T to) {
        return lt.compareTo(from) >= 0 && lt.compareTo(to) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

