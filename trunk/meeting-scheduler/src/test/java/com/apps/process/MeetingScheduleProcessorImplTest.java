package com.apps.process;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.apps.parser.MeetingRequestParser;

@RunWith(MockitoJUnitRunner.class)
public class MeetingScheduleProcessorImplTest {

    @Mock
    MeetingRequestParser meetingRequestParser;

    @InjectMocks
    MeetingScheduleProcessorImpl meetingScheduleProcessorImpl;

    @Test(expected = IllegalArgumentException.class)
    public void testProcess_WithLessThan3Lines() {

        String dummyInvalidText = "0900 1730\r\n" + "2011-03-17 10:17:06 EMP001\r\n";

        meetingScheduleProcessorImpl.process(dummyInvalidText);

    }

}
