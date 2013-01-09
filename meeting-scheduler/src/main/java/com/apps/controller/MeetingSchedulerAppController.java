package com.apps.controller;

/**
 * Meeting Scheduler App Controller
 * 
 * This program by default use a dummy input string as provided in the test
 * instructions. To provide your own value, run this program using command line
 * argument 'true' and it will start listening to console entry. When done
 * with your input - enter 'done' and it will process the input string
 * 
 * Program is written in a way that it uses some default office hours. It will
 * try to parse the user provided office hours and in case its not in valid
 * format - it will use the default value and continue parsing remaining data
 * 
 * It expects minimum 3 lines to be provided to give any meaningful output else
 * will show an error message.
 * 
 * Input should must be in following format -
 * 
 * [HHmm] [HHmm] [request submission time, in the format YYYY-MM-DD HH:MM:SS]
 * [employee id] [meeting start time, in the format YYYY-MM-DD HH:MM] [meeting
 * duration in hours]
 * 
 * meeting request data is derived from 2 lines together. In case even if one of
 * them is invalid, that meeting request data will not be processed and warning
 * message will be printed about the parse failure. Valid set of meeting request
 * data will be continued to parse and output will be shown accordingly.
 * 
 * NOTE - Input data is received by the application using InputFetcher. Factory
 * pattern has been used to allow different types of input sources example -
 * hardcoded dummy input (provided in this app), console based input fetcher
 * (provided in this app), fetching input text from external file system etc. (
 * can be easily added into factory) and hence this allows future proofing the
 * application for new input sources
 * 
 * NOTE - It can be modified easily to fail even if one of the meeting request
 * data pair line is not proper, but to make it more robust - it ignores
 * invalid lines. 
 * 
 * NOTE - Dependencies are injected directly as object into this
 * main class. Proper frameworks can be used in future to use IOC for example -
 * Spring etc. 
 * NOTE - To keep application simple, meeting duration is assumed to
 * be allowed only as whole number between 1 to 8 hours. 
 * NOTE - console based logging is used in the exercise to keep things simple. 
 * I can easily use log4j or logscape, but left it for later implementation.
 * 
 * NOTE: Processing is done in following steps -
 * 
 * 1. Read office timing data 
 * 2. Read input data in pair of 2 lines at a time to
 * get meeting requests 
 * 3. Create a TreeMap with key as meetingDate and value as
 * LinkedList of all meetingRequest for that particular date 
 * 4. Further process
 * meetingRequest for each day as below - 
 *   a. Loop through unique meeting dates (ie. keys of map created in step 3) 
 *   b. Sort the Linked List for each meeting date (comparator is provided to 
 *      allow sorting based on meeting start time) 
 *   c. Then all meeting request for a meeting date are iterated if the next 
 *      meeting overlap the previous meeting. If overlap is found then submission 
 *      date is checked. One which has early submission date is given preference 
 *      and other request is removed. 
 *   d. Meeting request is also validated for office opening hour timings 
 *      and accordingly removed if fall outside the interval 
 * 5. This gives a processed SortedMap (TreeMap) having meeting date as keys and
 * LinkedList of processed meeting request for each day. 6. Finally the
 * processed map is printed as per the required format
 * 
 * 
 */
public interface MeetingSchedulerAppController {

    /**
     * method to start execution of application
     */
    void execute(String[] inputArgs);
}
