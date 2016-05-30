package service;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ZENIT on 24.05.2016.
 */
public class DateConverter {
    static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    static final String SHORT_PATTERN = "yyyy-MM-dd HH:mm";

    public static java.util.Date convertSqlDateToDate(String date) {
        java.util.Date newDate = null;

        SimpleDateFormat format = new SimpleDateFormat(PATTERN);
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            new RuntimeException(e);
        }

        return newDate;
    }

    public static java.util.Date convertDateToSqlDate(String date) {
        java.util.Date newDate = null;

        SimpleDateFormat format = new SimpleDateFormat(PATTERN);
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            new RuntimeException(e);
        }

        return newDate;
    }

    public static String converdDatetoStr (java.util.Date date) {
        SimpleDateFormat format = new SimpleDateFormat(SHORT_PATTERN);
        return format.format(date);
    }

    public static java.util.Date convertControlDateToDate(String date) {
        java.util.Date newDate = null;

        SimpleDateFormat format = new SimpleDateFormat(SHORT_PATTERN);
        try {
            newDate = format.parse(date.replace("T", " "));
        } catch (ParseException e) {
            new RuntimeException(e);
        }

        return newDate;
    }
}
