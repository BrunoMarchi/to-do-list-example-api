package com.example.todolist;

import io.micrometer.core.instrument.util.StringUtils;

public class Utils {
    public static boolean isValidDescription(String description) {
        if(description == null || StringUtils.isBlank(description)
                || StringUtils.isEmpty(description) || description.length() > 100) {
            return false;
        }
        return true;
    }
}
