package com.apps.process;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedMap;
import java.util.TreeMap;

import com.apps.domain.MeetingRequest;
import com.apps.domain.OfficeTiming;
import com.apps.parser.MeetingRequestParser;
import com.apps.parser.MeetingRequestParserImpl;
import com.apps.util.DateUtils;

/**
 * Implementation class for MeetingScheduleProcessor
 * 
 * This class contains most of the logic required to read the input text data
 * and then processing it to valid meeting requests
 * 
 * Processing is done in following steps -
 * 
 * 1. Read office timing data 2. Read input data in pair of 2 lines at a time to
 * get meeting requests 3. Create a TreeMap with key as meetingDate and value as
 * LinkedList of all meetingRequest for that particular date 4. Further process
 * meetingRequest for each day as below - a. Loop through unique meeting dates
 * (ie. keys of map created in step 3) b. Sort the Linked List for each meeting
 * date (comparator is provided to allow sorting based on meeting start time) c.
 * Then all meeting request for a meeting date are iterated if the next meeting
 * overlap the previous meeting. If overlap is found then submission date is
 * checked. One which has early submission date is given preference and other
 * request is removed. d. Meeting request is also validated for office opening
 * hour timings and accordingly removed if fall outside the interval 5. This
 * gives a processed SortedMap (TreeMap) having meeting date as keys and
 * LinkedList of processed meeting request for each day. 6. Finally the
 * processed map is printed as per the required format
 * 
 * 
 * @author agautam
 * 
 */
public class MeetingScheduleProcessorImpl implements MeetingScheduleProcessor {

    /**
     * Meeting request parser instance which helps in parsing the text data into
     * meaningful MeetingRequest object (validation is included) Also, it
     * provide method for parsing office timing into OfficeTiming object (give
     * neccessary default value if input is not correct for office timings)
     */
    private MeetingRequestParser meetingRequestParser = new MeetingRequestParserImpl();

    /**
     * Method to process the meeting request input data.
     */
    public SortedMap<Date, List<MeetingRequest>> process(String rawInputData) {

        System.out.println("******************INPUT******************");
        System.out.println(rawInputData);

        // converting input text lines into string array
        String inputDataArray[] = parseInputStringToArray(rawInputData);

        if (inputDataArray.length < 3) {
            throw new IllegalArgumentException("At least 3 lines are required in the correct format for processing");
        }

        // parsing office timings
        OfficeTiming officeTiming = meetingRequestParser.parseOfficeTiming(inputDataArray[0]);

        SortedMap<Date, List<MeetingRequest>> unProcessedMeetingRequestMap = createMeetingRequestMap(inputDataArray); // creating
                                                                                                                      // unprocessed
                                                                                                                      // map
                                                                                                                      // of
                                                                                                                      // meeting
                                                                                                                      // requests

        SortedMap<Date, List<MeetingRequest>> processedMeetingRequestMap = processMeetingRequestMap(
                unProcessedMeetingRequestMap, officeTiming); // process meeting
                                                             // request map as
                                                             // per business
                                                             // logic

        return processedMeetingRequestMap;
    }

    /**
     * 1. Read input data in pair of 2 lines at a time to get meeting requests *
     * 2. Create a TreeMap with key as meetingDate and value as LinkedList of
     * all meetingRequest for that particular date *
     * 
     * @param inputDataArray
     * @return SortedMap - unprocessed.
     */
    private SortedMap<Date, List<MeetingRequest>> createMeetingRequestMap(String[] inputDataArray) {
        MeetingRequest meetingRequest = null;
        SortedMap<Date, List<MeetingRequest>> unProcessedMeetingRequestMap = new TreeMap<Date, List<MeetingRequest>>();
        List<MeetingRequest> meetingRequestList = null;

        for (int i = 1; i < (inputDataArray.length - 1); i = i + 2) {
            try {
                meetingRequest = meetingRequestParser.parseMeetingRequestData(inputDataArray[i], inputDataArray[i + 1]);
            } catch (Exception e) {
                System.out.println("WARN: Failed parsing of meeting request data for input: " + inputDataArray[i] + " "
                        + inputDataArray[i + 1]);
            }

            if (null != meetingRequest) {
                if (unProcessedMeetingRequestMap.containsKey(meetingRequest.getMeetingDate())) {
                    meetingRequestList = unProcessedMeetingRequestMap.get(meetingRequest.getMeetingDate());
                } else {
                    meetingRequestList = new LinkedList<MeetingRequest>();
                    unProcessedMeetingRequestMap.put(meetingRequest.getMeetingDate(), meetingRequestList);
                }
                meetingRequestList.add(meetingRequest);
            }
        }

        return unProcessedMeetingRequestMap;

    }

    /**
     * Loop through unique meeting dates (ie. keys of map created in step 3)
     * Sort the Linked List for each meeting date (comparator is provided to
     * allow sorting based on meeting start time) Then all meeting request for a
     * meeting date are iterated if the next meeting overlap the previous
     * meeting. If overlap is found then submission date is checked. One which
     * has early submission date is given preference and other request is
     * removed. Meeting request is also validated for office opening hour
     * timings and accordingly removed if fall outside the interval This gives a
     * processed SortedMap (TreeMap) having meeting date as keys and LinkedList
     * of processed meeting request for each day.
     * 
     * @param unProcessedMeetingRequestMap
     * @param officeTiming
     * @return SortedMap - processed.
     */
    private SortedMap<Date, List<MeetingRequest>> processMeetingRequestMap(
            SortedMap<Date, List<MeetingRequest>> unProcessedMeetingRequestMap, OfficeTiming officeTiming) {
        List<MeetingRequest> meetingRequestList = null;
        MeetingRequest meetingRequest = null;
        Date officeStartTime = null;
        Date officeEndTime = null;
        SortedMap<Date, List<MeetingRequest>> processedMeetingRequestMap = new TreeMap<Date, List<MeetingRequest>>();

        for (Date meetingDate : unProcessedMeetingRequestMap.keySet()) {
            meetingRequestList = unProcessedMeetingRequestMap.get(meetingDate);

            Collections.sort(meetingRequestList);

            officeStartTime = new Date(meetingDate.getTime() + officeTiming.getOfficeStartTime());
            officeEndTime = new Date(meetingDate.getTime() + officeTiming.getOfficeEndTime());

            ListIterator<MeetingRequest> iter = meetingRequestList.listIterator();

            meetingRequest = getNextValidMeetingRequest(iter, officeStartTime, officeEndTime);

            MeetingRequest meetingRequestNext = null;
            while (meetingRequest != null
                    && (meetingRequestNext = getNextValidMeetingRequest(iter, officeStartTime, officeEndTime)) != null) {

                if (meetingRequest.getMeetingEndTime().getTime() > meetingRequestNext.getMeetingStartTime().getTime()) {
                    if (meetingRequest.getSubmissionTime().getTime() < meetingRequestNext.getSubmissionTime().getTime()) {
                        iter.remove(); // remove the latest returned value as it
                                       // overlaps and submitted later
                    } else {
                        iter.previous();
                        iter.remove();
                        meetingRequest = meetingRequestNext;
                    }
                } else {
                    meetingRequest = meetingRequestNext;
                }

            }
            if (null != meetingRequest) {
                processedMeetingRequestMap.put(meetingRequest.getMeetingDate(), meetingRequestList);
            }
        }

        return processedMeetingRequestMap;
    }

    /**
     * Recursive method that keeps getting next meeting request object until it
     * gets a valid request falling within office hours. When it gets invalid
     * meeting request as per office hours it removes it from the list.
     * 
     * @param iter
     *            - LinkedList Iterator
     * @param officeStartTime
     * @param officeEndTime
     * @return MeetingRequest
     */
    private static MeetingRequest getNextValidMeetingRequest(ListIterator<MeetingRequest> iter, Date officeStartTime,
            Date officeEndTime) {
        MeetingRequest meetingRequest = null;
        while (iter.hasNext() && meetingRequest == null) {
            meetingRequest = iter.next();
            if (meetingRequest.getMeetingStartTime().getTime() < officeStartTime.getTime()
                    || meetingRequest.getMeetingEndTime().getTime() > officeEndTime.getTime()) {
                iter.remove();
                meetingRequest = getNextValidMeetingRequest(iter, officeStartTime, officeEndTime);
            }
        }

        return meetingRequest;
    }

    private String[] parseInputStringToArray(String rawText) {

        return rawText.split("\r\n");
    }

}
