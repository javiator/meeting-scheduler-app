package com.apps;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.apps.controller.MeetingSchedulerAppController;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class MeetingSchedulerAppTest {

    @Mock
    private MeetingSchedulerAppController meetingSchedulerAppController;

    private ArgumentCaptor<String[]> argumentCaptor = ArgumentCaptor.forClass(String[].class);;

    @Test
    public void testWithEmptyArgumentStringArray() {
        String[] argumentArray = new String[] {};

        MeetingSchedulerApp.controller = meetingSchedulerAppController;

        doNothing().when(meetingSchedulerAppController).execute(any(String[].class));

        MeetingSchedulerApp.main(argumentArray);

        verify(meetingSchedulerAppController).execute(argumentCaptor.capture());

        Assert.assertArrayEquals(argumentArray, argumentCaptor.getValue());
    }

    @Test
    public void testNonEmptyArgumentStringArray() {
        String[] argumentArray = new String[] { "true" };

        MeetingSchedulerApp.controller = meetingSchedulerAppController;

        doNothing().when(meetingSchedulerAppController).execute(any(String[].class));

        MeetingSchedulerApp.main(argumentArray);

        verify(meetingSchedulerAppController).execute(argumentCaptor.capture());

        Assert.assertArrayEquals(argumentArray, argumentCaptor.getValue());
    }
}
