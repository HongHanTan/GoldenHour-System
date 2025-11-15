package com.goldenhour.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    public static String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
    }
}
