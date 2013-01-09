package com.apps.parser;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.apps.domain.MeetingRequest;
import com.apps.domain.OfficeTiming;
import com.apps.exception.InvalidMeetingRequestDataException;
import com.apps.util.DateUtils;

/**
 * This class helps to parse the meeting request raw data strings into valid
 * MeetingRequest Object. It also provide method for parsing office timings into
 * a valid OfficeTimings object.
 * 
 * Regex pattern is mainly used here to verify that the input string data is in
 * the correct format or not. If valid, patterns are further used to fetch
 * specific values like date, duration etc from the string data without doing
 * another operation of parsing the string
 * 
 * 
 * @author agautam
 * 
 */
public class MeetingRequestParserImpl implements MeetingRequestParser {

    private static Pattern meetingRequestSubmissionPattern;
    private static Pattern meetingSchedulePattern;
    private static Pattern officeTimingPattern;

    /**
     * constructor for initializing reg-ex patterns and date formats to be used
     * during the parsing of raw input request data
     */
    public MeetingRequestParserImpl() {
        meetingRequestSubmissionPattern = Pattern.compile(MEETING_REQUEST_SUBMISSION_PATTERN);
        meetingSchedulePattern = Pattern.compile(MEETING_REQUEST_PATTERN);
        officeTimingPattern = Pattern.compile(OFFICE_TIMING_PATTERN);
    }

    public OfficeTiming parseOfficeTiming(String officeTimingText) {

        Matcher officeTimingMatcher = officeTimingPattern.matcher(officeTimingText);
        OfficeTiming officeTiming = new OfficeTiming();
        if (officeTimingMatcher.matches()) {
            officeTimingMatcher.reset();
            officeTimingMatcher.find();

            officeTiming.setOfficeStartTime(Integer.parseInt(officeTimingMatcher.group(2)) * hourInMillis
                    + Integer.parseInt(officeTimingMatcher.group(3)) * minuteInMillis);
            officeTiming.setOfficeEndTime(Integer.parseInt(officeTimingMatcher.group(6)) * hourInMillis
                    + Integer.parseInt(officeTimingMatcher.group(7)) * minuteInMillis);

        } else {
            System.out
                    .println(String
                            .format("WARN: Parsing of officeTiming failed from input data: %s, Will use default values startime %s and endtime %s",
                                    officeTimingText, officeTiming.getOfficeStartTime(),
                                    officeTiming.getOfficeEndTime()));
        }
        return officeTiming;
    }

    public MeetingRequest parseMeetingRequestData(String meetingRequestSubmissionText, String meetingScheduleText)
            throws InvalidMeetingRequestDataException {

        Matcher meetingRequestSubmissionMatcher = meetingRequestSubmissionPattern.matcher(meetingRequestSubmissionText);
        Matcher meetingScheduleMatcher = meetingSchedulePattern.matcher(meetingScheduleText);

        MeetingRequest meetingRequest = null;

        if (meetingRequestSubmissionMatcher.matches() && meetingScheduleMatcher.matches()) {

            meetingRequestSubmissionMatcher.reset();
            meetingRequestSubmissionMatcher.find();

            meetingScheduleMatcher.reset();
            meetingScheduleMatcher.find();

            // this will verify that meeting date and meeting submission date
            // have valid values
            if (validateDate(meetingRequestSubmissionMatcher.group(2), // this
                                                                       // will
                                                                       // fetch
                                                                       // year
                    meetingRequestSubmissionMatcher.group(4), // this will fetch
                                                              // month
                    meetingRequestSubmissionMatcher.group(5)) // this will fetch
                                                              // day
                    && validateDate(meetingScheduleMatcher.group(2), // this
                            // will
                            // fetch
                            // year
                            meetingScheduleMatcher.group(4), // this
                                                             // will
                                                             // fetch
                            // month
                            meetingScheduleMatcher.group(5))

            ) {
                meetingRequest = new MeetingRequest();

                try {
                    meetingRequest.setSubmissionTime(DateUtils.toDateTime(meetingRequestSubmissionMatcher.group(1)));
                    meetingRequest.setMeetingStartTime(DateUtils.toDateTimeWithoutSeconds(meetingScheduleMatcher
                            .group(1)));
                    meetingRequest.setMeetingDuration(Integer.parseInt(meetingScheduleMatcher.group(10)));
                    meetingRequest.setMeetingDate(DateUtils.toDate(meetingScheduleMatcher.group(1)));
                    meetingRequest.setRequesterEmpId(meetingRequestSubmissionMatcher.group(11));

                    /**
                     * setting meeting end time by calculating as start time +
                     * meeting duration
                     */
                    meetingRequest.setMeetingEndTime(new Date(meetingRequest.getMeetingStartTime().getTime()
                            + meetingRequest.getMeetingDuration() * hourInMillis));

                } catch (ParseException e) {
                    meetingRequest = null; // setting this to null as its not
                                           // having valid data. parsing failed
                                           // due to exception
                }

            }
        }
        /**
         * checking if meetingRequest object is null. If the value is null, that
         * means parsing of the request data failed and hence throwing
         * exception.
         */
        if (null == meetingRequest) {
            throw new InvalidMeetingRequestDataException("Invalid meeting request data");
        }

        return meetingRequest;

    }

    /**
     * This method allows validating the date value if its a valid combination
     * for a valid date or not
     * 
     * 
     * @param strYear
     *            - value of year
     * @param month
     *            - value of month
     * @param day
     *            - value of day
     * @return boolean - true if valid otherwise false.
     */
    private boolean validateDate(String strYear, String month, String day) {

        int year = Integer.parseInt(strYear);

        if (day.equals("31")
                && (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")
                        || month.equals("04") || month.equals("06") || month.equals("09"))) {
            return false; // only 1,3,5,7,8,10,12 has 31 days
        } else if (month.equals("2") || month.equals("02")) {
            // leap year
            if (year % 4 == 0) {
                if (day.equals("30") || day.equals("31")) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (day.equals("29") || day.equals("30") || day.equals("31")) {
                    return false;
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }

    }

}
