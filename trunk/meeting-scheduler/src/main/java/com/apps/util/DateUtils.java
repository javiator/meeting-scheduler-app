package com.apps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date Utility class for doing various convesions between string to date and
 * vice versa
 * 
 * @author agautam
 * 
 */
public class DateUtils {

    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_WITHOUT_SECONDS_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private static SimpleDateFormat timeFormat;
    private static SimpleDateFormat dateTimeFormat;
    private static SimpleDateFormat dateTimeWithoutSecondsFormat;
    private static SimpleDateFormat dateFormat;

    static {
        timeFormat = new SimpleDateFormat(TIME_FORMAT);
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateTimeFormat = new SimpleDateFormat(DATETIME_FORMAT);
        dateTimeWithoutSecondsFormat = new SimpleDateFormat(DATETIME_WITHOUT_SECONDS_FORMAT);
    }

    /**
     * Method to convert date object in formatted time string
     * 
     * @param date
     * @return String in format of HH:mm
     */
    public static String toFormattedTime(Date date) {
        return timeFormat.format(date);
    }

    /**
     * Method to convert date object to formatted date string
     * 
     * @param date
     * @return String in format of yyyy-MM-dd
     */
    public static String toFormattedDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * Method to convert string in format of yyyy-MM-dd HH:mm:ss to date object
     * 
     * @param stringDate
     *            in format of yyyy-MM-dd HH:mm:ss
     * @return Date object
     * @throws ParseException
     */
    public static Date toDateTime(String stringDate) throws ParseException {
        return dateTimeFormat.parse(stringDate);
    }

    /**
     * Method to convert string in format of yyyy-MM-dd HH:mm to date object
     * without seconds details
     * 
     * @param stringDate
     *            in format of yyyy-MM-dd HH:mm
     * @return Date object
     * @throws ParseException
     */
    public static Date toDateTimeWithoutSeconds(String stringDateWithoutSeconds) throws ParseException {
        return dateTimeWithoutSecondsFormat.parse(stringDateWithoutSeconds);
    }

    /**
     * Method to convert string in format of yyyy-MM-dd to date object ignoring
     * time value
     * 
     * @param stringDate
     *            in format of yyyy-MM-dd
     * @return Date object
     * @throws ParseException
     */
    public static Date toDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * Method to convert date object having time component to date object
     * without time
     * 
     * @param date
     *            object
     * @return Date object
     * @throws ParseException
     */
    public static Date toDateWithoutTime(Date date) throws ParseException {
        return dateFormat.parse(dateFormat.format(date));
    }
}
