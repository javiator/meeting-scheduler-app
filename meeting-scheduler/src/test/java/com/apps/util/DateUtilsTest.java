package com.apps.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class DateUtilsTest {

    private static final String strDate = "2011-03-17";
    private static final String strDateTime1 = "2011-03-17 10:17:06";
    private static final String strDateTime2 = "2012-08-30 14:54:06";
    private static final String strDateTimeWithoutSeconds = "2011-03-17 10:17";
    private SimpleDateFormat simpleDateFormat;

    @Test
    public void toFormattedTimeTest() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = simpleDateFormat.parse(strDateTime1);

        Assert.assertEquals("10:17", DateUtils.toFormattedTime(date));

        date = simpleDateFormat.parse(strDateTime2);

        Assert.assertEquals("14:54", DateUtils.toFormattedTime(date));

    }

    @Test
    public void toFormattedDateTest() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = simpleDateFormat.parse(strDateTime1);

        Assert.assertEquals("2011-03-17", DateUtils.toFormattedDate(date));

        date = simpleDateFormat.parse(strDateTime2);

        Assert.assertEquals("2012-08-30", DateUtils.toFormattedDate(date));

    }

    @Test
    public void toDateTimeTest() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = simpleDateFormat.parse(strDateTime1);

        Assert.assertEquals(date, DateUtils.toDateTime(strDateTime1));

        date = simpleDateFormat.parse(strDateTime2);

        Assert.assertEquals(date, DateUtils.toDateTime(strDateTime2));

    }

    @Test
    public void toDateTimeWithoutSecondsTest() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.toDateTimeWithoutSeconds(strDateTimeWithoutSeconds));

        Assert.assertEquals(0, calendar.get(Calendar.SECOND));
        Assert.assertEquals(2, calendar.get(Calendar.MONTH));

    }

    @Test
    public void toDateTest() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.toDate(strDate));

        Assert.assertEquals(0, calendar.get(Calendar.SECOND));
        Assert.assertEquals(0, calendar.get(Calendar.MINUTE));
        Assert.assertEquals(2011, calendar.get(Calendar.YEAR));
        Assert.assertEquals(2, calendar.get(Calendar.MONTH));
        Assert.assertEquals(17, calendar.get(Calendar.DATE));

    }

    @Test
    public void toDateWithoutTimeTest() throws ParseException {

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = simpleDateFormat.parse(strDateTime1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.toDateWithoutTime(date));

        Assert.assertEquals(0, calendar.get(Calendar.SECOND));
        Assert.assertEquals(0, calendar.get(Calendar.MINUTE));
        Assert.assertEquals(2011, calendar.get(Calendar.YEAR));
        Assert.assertEquals(2, calendar.get(Calendar.MONTH));
        Assert.assertEquals(17, calendar.get(Calendar.DATE));

    }

}
