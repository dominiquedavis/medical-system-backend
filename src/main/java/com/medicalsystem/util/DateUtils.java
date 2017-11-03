package com.medicalsystem.util;

import lombok.extern.java.Log;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Log
public final class DateUtils {

    private static final String[] dateFormats = {
            "d.M.y",
            "M/d/y"
    };

    private DateUtils() {}

    public static LocalDate fromString(String str) throws ParseException {
        Date date = org.apache.commons.lang3.time.DateUtils.parseDateStrictly(str, dateFormats);
        return toLocalDate(date);
    }

    public static Date toUtilDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
