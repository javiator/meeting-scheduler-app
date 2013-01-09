package com.apps.parser;

import com.apps.domain.MeetingRequest;
import com.apps.domain.OfficeTiming;
import com.apps.exception.InvalidMeetingRequestDataException;

/**
 * Interface to provide methods for parsing meeting request data. It also gives
 * the pattern definition required to parse input request string using regex.
 * 
 * @author agautam
 * 
 */
public interface MeetingRequestParser {

    /**
     * Pattern to validate/read 1st line of meeting request data having meeting
     * submission date information
     * 
     * [request submission time, in the format YYYY-MM-DD HH:MM:SS] [employee
     * id]
     * 
     */
    public static final String MEETING_REQUEST_SUBMISSION_PATTERN = "(((19|20)\\d\\d)-(1[012]|0?[1-9])-([12][0-9]|3[01]|0?[1-9])(\\s)(1[0-9]|2[0-23]|0?[1-9]):(00|[12345][0-9]|0?[1-9]):(00|[12345][0-9]|60|0?[1-9]))(\\s)(EMP\\d\\d\\d)";

    /**
     * Pattern to validate/read 2nd line of meeting request data having meeting
     * start time information
     * 
     * NOTE - To keep application simple, duration is assumed to be allowed only
     * as whole number between 1 to 8 hours.
     * 
     * [meeting start time, in the format YYYY-MM-DD HH:MM] [meeting duration in
     * hours]
     * 
     */
    public static final String MEETING_REQUEST_PATTERN = "(((19|20)\\d\\d)-(1[012]|0?[1-9])-([12][0-9]|3[01]|0?[1-9])(\\s)(1[0-9]|2[0-23]|0?[1-9]):(00|[12345][0-9]|0?[1-9]))(\\s)([1-8])";

    /**
     * Pattern to validate/read office timing data
     * 
     * [startTime in the format HH:mm] [endTime in the format HH:mm]
     * 
     */
    public static final String OFFICE_TIMING_PATTERN = "((1[0-9]|2[0-23]|0?[0-9])([12345][0-9]|0?[0-9]))(\\s)((1[0-9]|2[0-23]|0?[0-9])([12345][0-9]|0?[0-9]))";

    /**
     * constant to store milliseconds per hour
     */
    public static final long hourInMillis = 60L * 60L * 1000L;

    /**
     * constant to store milliseconds per minute
     */
    public static final long minuteInMillis = 60L * 1000L;

    /**
     * Method to parse Meeting Request Text Data into proper MeetingRequest
     * data.
     * 
     * @param meetingRequestSubmissionText
     * @param meetingScheduleText
     * @return MeetingRequest object
     * @throws InvalidMeetingRequestDataException
     */
    MeetingRequest parseMeetingRequestData(String meetingRequestSubmissionText, String meetingScheduleText)
            throws InvalidMeetingRequestDataException;

    /**
     * Method to parse office timings
     * 
     * @param officeTimingText
     * @return officeTiming object
     */
    OfficeTiming parseOfficeTiming(String officeTimingText);
}
