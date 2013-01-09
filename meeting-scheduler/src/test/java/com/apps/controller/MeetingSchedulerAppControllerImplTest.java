package com.apps.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.apps.domain.MeetingRequest;
import com.apps.input.InputFetcher;
import com.apps.input.InputFetcherFactory;
import com.apps.parser.MeetingRequestParserImplTest;
import com.apps.process.MeetingScheduleProcessorImpl;

@RunWith(MockitoJUnitRunner.class)
public class MeetingSchedulerAppControllerImplTest {

    @Mock
    InputFetcher inputFetcher;

    @Mock
    InputFetcherFactory inputFetcherFactory;

    @Mock
    MeetingScheduleProcessorImpl processor;

    @Mock
    SortedMap<Date, List<MeetingRequest>> processedMeetingRequestMap;

    @InjectMocks
    MeetingSchedulerAppControllerImpl meetingSchedulerAppController;

    ArgumentCaptor<String> processorArgumentCaptor = ArgumentCaptor.forClass(String.class);

    ArgumentCaptor<Integer> inputFetcherFactoryArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

    @Test
    public void testExecute() throws ParseException {

        String dummyInputText = "input string";

        when(inputFetcherFactory.getInputFetcher(anyInt())).thenReturn(inputFetcher);
        when(inputFetcher.getInputData()).thenReturn(dummyInputText);
        when(processor.process(any(String.class))).thenReturn(processedMeetingRequestMap);

        meetingSchedulerAppController.execute(new String[] { "true" });

        verify(inputFetcherFactory).getInputFetcher(inputFetcherFactoryArgumentCaptor.capture());
        verify(processor).process(processorArgumentCaptor.capture());

        Assert.assertEquals(new Integer(1), inputFetcherFactoryArgumentCaptor.getValue());
        Assert.assertEquals(dummyInputText, processorArgumentCaptor.getValue());

    }

    @Test
    public void testExecute_WithValidMeetingRequestData() throws ParseException {

        String dummyInputText = "input string";

        // process input text
        SortedMap<Date, List<MeetingRequest>> processedMeetingRequestMap = new TreeMap<Date, List<MeetingRequest>>();

        MeetingRequest meetingRequest1 = MeetingRequestParserImplTest.generateMeetingRequest("2011-03-17 10:17:06",
                "2011-03-21", 2, "EMP001", "2011-03-21 09:00");
        MeetingRequest meetingRequest2 = MeetingRequestParserImplTest.generateMeetingRequest("2011-03-16 09:28:23",
                "2011-03-22", 2, "EMP003", "2011-03-22 14:00");
        MeetingRequest meetingRequest3 = MeetingRequestParserImplTest.generateMeetingRequest("2011-03-17 10:17:06",
                "2011-03-22", 1, "EMP004", "2011-03-22 16:00");

        List<MeetingRequest> meetingRequestList1 = new LinkedList<MeetingRequest>();
        List<MeetingRequest> meetingRequestList2 = new LinkedList<MeetingRequest>();

        meetingRequestList1.add(meetingRequest1);
        meetingRequestList2.add(meetingRequest2);
        meetingRequestList2.add(meetingRequest3);

        processedMeetingRequestMap.put(meetingRequest1.getMeetingDate(), meetingRequestList1);
        processedMeetingRequestMap.put(meetingRequest2.getMeetingDate(), meetingRequestList2);

        when(inputFetcherFactory.getInputFetcher(anyInt())).thenReturn(inputFetcher);
        when(inputFetcher.getInputData()).thenReturn(dummyInputText);
        when(processor.process(any(String.class))).thenReturn(processedMeetingRequestMap);

        meetingSchedulerAppController.execute(new String[] {});

        verify(inputFetcherFactory).getInputFetcher(inputFetcherFactoryArgumentCaptor.capture());
        verify(processor).process(processorArgumentCaptor.capture());

        Assert.assertEquals(new Integer(0), inputFetcherFactoryArgumentCaptor.getValue());
        Assert.assertEquals(dummyInputText, processorArgumentCaptor.getValue());

    }
}
