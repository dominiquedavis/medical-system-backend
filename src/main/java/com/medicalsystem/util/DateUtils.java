package com.medicalsystem.util;

import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Log
public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(""
            //+ "[M/d/yy]"
            + "[M/d/y]"
            //+ "[d.M.yy]"
            + "[d.M.y]"
            //+ "[d-M-yy]"
            + "[d-M-y]"
    );

    public static LocalDate fromString(String str) {
        str = str.trim();

        if (str.equals("0") || str.equals("x"))
            return null;

        LocalDate date = null;
        try {
            date = LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            log.severe("Error parsing date: " + str);
        }

        return date;
    }

}
