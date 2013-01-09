package com.apps.controller;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import com.apps.domain.MeetingRequest;
import com.apps.input.InputFetcher;
import com.apps.input.InputFetcherFactory;
import com.apps.input.InputFetcherFactoryImpl;
import com.apps.process.MeetingScheduleProcessor;
import com.apps.process.MeetingScheduleProcessorImpl;
import com.apps.util.DateUtils;

/**
 * @inheritDoc
 *
 */
public class MeetingSchedulerAppControllerImpl implements MeetingSchedulerAppController {

    /**
     * Processor to process input meeting request data
     */
    private MeetingScheduleProcessor processor = new MeetingScheduleProcessorImpl();

    /**
     * Factory pattern is used to select type of input fetcher to get the input
     * meeting request raw data string.
     */
    private InputFetcherFactory inputFetcherFactory = new InputFetcherFactoryImpl();

    private static final String USAGE_HELP_FOR_USER_PROVIDED_INPUT = "/**************************************************************************/\r\n"
            + "/*THIS PROGRAM BY DEFAULT USE A DUMMY INPUT MEETING REQUEST */\r\n"
            + "/*TO OVERRIDE DEFAULT VALUE WITH YOUR OWN, RUN THIS PROGRAM WITH COMMAND LINE ARGUMENT 'TRUE'*/\r\n"
            + "/*WHEN PROVIDING YOUR OWN DATE, IT EXPECTS MULTIPLE LINES TO BE ENTERED*/\r\n"
            + "/*AFTER PUTTING ALL THE LINES, ENTER 'DONE' AND IT WILL PROCESS THE COMPLETE DATA*/\r\n"
            + "/**************************************************************************/\r\n";

    /**
     * @inheritDoc
     *
     */
    public void execute(String[] inputArgs) {

        String rawScheduleText = null;
        InputFetcher inputFetcher = null;

        System.out.println(USAGE_HELP_FOR_USER_PROVIDED_INPUT);

        // checks if user has given command line argument as 'true' and
        // accordingly allow user to give input on console
        if (inputArgs.length > 0 && inputArgs[0].equalsIgnoreCase("true")) {
            inputFetcher = inputFetcherFactory.getInputFetcher(1);
        } else {
            inputFetcher = inputFetcherFactory.getInputFetcher(0);
        }

        rawScheduleText = inputFetcher.getInputData();

        try {
            // process input text
            SortedMap<Date, List<MeetingRequest>> processedMeetingRequestMap = processor.process(rawScheduleText);

            printOutput(processedMeetingRequestMap); // printing processed meeting requests

        } catch (Exception e) {
            System.out.println("Some error has occured during processing of your request" + e.getMessage());
        }

    }

    /**
     * Method to print the processed meeting request map having the processed
     * meetings
     * 
     * @param processedMeetingRequestMap
     */
    private static void printOutput(SortedMap<Date, List<MeetingRequest>> processedMeetingRequestMap) {
        List<MeetingRequest> meetingRequestList = null;
        System.out.println("******************OUTPUT******************");
        for (Date meetingDate : processedMeetingRequestMap.keySet()) {

            System.out.println(DateUtils.toFormattedDate(meetingDate));
            meetingRequestList = processedMeetingRequestMap.get(meetingDate);
            for (MeetingRequest meetingRequest : meetingRequestList)
                System.out.println(String.format("%s %s %s",
                        DateUtils.toFormattedTime(meetingRequest.getMeetingStartTime()),
                        DateUtils.toFormattedTime(meetingRequest.getMeetingEndTime()),
                        meetingRequest.getRequesterEmpId()));
        }
    }

}
