package com.dailycodebuffer.demo.utils;

import java.util.Objects;

public class AppUtils {
    public static boolean validateStringNonNull(String value) {
        if (Objects.nonNull(value) &&
                !"".equalsIgnoreCase(value)) {
            return true;
        }
        return false;
    }
}
