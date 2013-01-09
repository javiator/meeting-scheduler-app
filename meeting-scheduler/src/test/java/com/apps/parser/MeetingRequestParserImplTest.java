package com.apps.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.apps.domain.MeetingRequest;
import com.apps.domain.OfficeTiming;
import com.apps.exception.InvalidMeetingRequestDataException;

public class MeetingRequestParserImplTest {

    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_WITHOUT_SECONDS_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final long hourInMillis = 60L * 60L * 1000L;

    private static final long minuteInMillis = 60L * 1000L;

    private static final MeetingRequestParserImpl parser = new MeetingRequestParserImpl();

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

    @Test
    public void testParseOfficeTiming_WithCorrectData() {

        MeetingRequestParserImpl parser = new MeetingRequestParserImpl();

        OfficeTiming officeTiming = parser.parseOfficeTiming("0900 1730");

        Assert.assertEquals((hourInMillis * 9), officeTiming.getOfficeStartTime());
        Assert.assertEquals(((hourInMillis * 17) + (minuteInMillis * 30)), officeTiming.getOfficeEndTime());

        officeTiming = parser.parseOfficeTiming("0955 1759");

        Assert.assertEquals(((hourInMillis * 9) + (minuteInMillis * 55)), officeTiming.getOfficeStartTime());
        Assert.assertEquals(((hourInMillis * 17) + (minuteInMillis * 59)), officeTiming.getOfficeEndTime());

    }

    @Test
    public void testParseOfficeTiming_WithInCorrectData() {

        OfficeTiming officeTiming = parser.parseOfficeTiming("0900 2830");

        Assert.assertEquals((hourInMillis * 9), officeTiming.getOfficeStartTime());
        Assert.assertEquals(((hourInMillis * 17) + (minuteInMillis * 30)), officeTiming.getOfficeEndTime());

        officeTiming = parser.parseOfficeTiming("0988 1759");

        Assert.assertEquals((hourInMillis * 9), officeTiming.getOfficeStartTime());
        Assert.assertEquals(((hourInMillis * 17) + (minuteInMillis * 30)), officeTiming.getOfficeEndTime());

        officeTiming = parser.parseOfficeTiming("0988 1759asdfasfasf");

        Assert.assertEquals((hourInMillis * 9), officeTiming.getOfficeStartTime());
        Assert.assertEquals(((hourInMillis * 17) + (minuteInMillis * 30)), officeTiming.getOfficeEndTime());
    }

    @Test
    public void testparseMeetingRequestData_WithCorrectData() throws InvalidMeetingRequestDataException, ParseException {

        MeetingRequest expectedMeetingRequest = generateMeetingRequest("2011-03-17 10:17:06", "2011-03-21", 2,
                "EMP001", "2011-03-21 09:00");

        MeetingRequest meetingRequest = parser.parseMeetingRequestData("2011-03-17 10:17:06 EMP001",
                "2011-03-21 09:00 2");

        Assert.assertEquals(expectedMeetingRequest.getSubmissionTime(), meetingRequest.getSubmissionTime());
        Assert.assertEquals(expectedMeetingRequest.getRequesterEmpId(), meetingRequest.getRequesterEmpId());
        Assert.assertEquals(expectedMeetingRequest.getMeetingDuration(), meetingRequest.getMeetingDuration());
        Assert.assertEquals(expectedMeetingRequest.getMeetingDate(), meetingRequest.getMeetingDate());
        Assert.assertEquals(expectedMeetingRequest.getMeetingStartTime(), meetingRequest.getMeetingStartTime());
        Assert.assertEquals(expectedMeetingRequest.getMeetingEndTime(), meetingRequest.getMeetingEndTime());

    }

    @Test(expected = InvalidMeetingRequestDataException.class)
    public void testparseMeetingRequestData_WithInCorrectData_Employee() throws InvalidMeetingRequestDataException,
            ParseException {

        parser.parseMeetingRequestData("2011-03-17 10:17:06 EMP0011", "2011-03-21 09:00 2");

    }

    @Test(expected = InvalidMeetingRequestDataException.class)
    public void testparseMeetingRequestData_WithInCorrectData_SubmissionDate1()
            throws InvalidMeetingRequestDataException, ParseException {

        parser.parseMeetingRequestData("2011-09-31 10:17:06 EMP001", "2011-03-21 09:00 2");

    }

    @Test(expected = InvalidMeetingRequestDataException.class)
    public void testparseMeetingRequestData_WithInCorrectData_SubmissionDate2()
            throws InvalidMeetingRequestDataException, ParseException {

        parser.parseMeetingRequestData("2011-02-30 10:17:06 EMP001", "2011-03-21 09:00 2");

    }

    @Test(expected = InvalidMeetingRequestDataException.class)
    public void testparseMeetingRequestData_WithInCorrectData_MeetingDate() throws InvalidMeetingRequestDataException,
            ParseException {

        parser.parseMeetingRequestData("2011-02-28 10:17:06 EMP001", "2011-02-30 09:00 2");

    }

    public static MeetingRequest generateMeetingRequest(String submissionTime, String meetingDate, int meetingDuration,
            String requesterEmpId, String meetingStartTime) throws ParseException {

        MeetingRequest meetingRequest = new MeetingRequest();

        meetingRequest.setSubmissionTime(dateTimeFormat.parse(submissionTime));
        meetingRequest.setMeetingDate(dateFormat.parse(meetingDate));
        meetingRequest.setMeetingDuration(meetingDuration);
        meetingRequest.setRequesterEmpId(requesterEmpId);
        meetingRequest.setMeetingStartTime(dateTimeWithoutSecondsFormat.parse(meetingStartTime));
        meetingRequest.setMeetingEndTime(new Date(meetingRequest.getMeetingStartTime().getTime() + hourInMillis
                * meetingDuration));

        return meetingRequest;

    }
}
