package com.medicalsystem.util;

public class StringUtils {

    public static String capitalize(String str) {
        String firstLetter = str.substring(0, 1);
        String theRest = str.substring(1);
        return firstLetter.toUpperCase() + theRest.toLowerCase();
    }

}
