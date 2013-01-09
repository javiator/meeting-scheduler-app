package com.apps.domain;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MeetingRequestTest {

    private String requesterEmpId;
    private Date meetingDate;
    private Date meetingStartTime;
    private Date meetingEndTime;
    private int meetingDuration;
    private Date submissionTime;

    @Before
    public void setup() {
        requesterEmpId = "EMP100";
        meetingDate = new Date();
        meetingStartTime = new Date();
        meetingEndTime = new Date();
        meetingDuration = 2;
        submissionTime = new Date();

    }

    @Test
    public void testMeetingRequest() {
        MeetingRequest meetingRequest = new MeetingRequest();

        meetingRequest.setMeetingDate(meetingDate);
        meetingRequest.setMeetingDuration(meetingDuration);
        meetingRequest.setMeetingEndTime(meetingEndTime);
        meetingRequest.setMeetingStartTime(meetingStartTime);
        meetingRequest.setRequesterEmpId(requesterEmpId);
        meetingRequest.setSubmissionTime(submissionTime);

        Assert.assertEquals(meetingDate, meetingRequest.getMeetingDate());
        Assert.assertEquals(meetingDuration, meetingRequest.getMeetingDuration());
        Assert.assertEquals(meetingEndTime, meetingRequest.getMeetingEndTime());
        Assert.assertEquals(requesterEmpId, meetingRequest.getRequesterEmpId());
        Assert.assertEquals(submissionTime, meetingRequest.getSubmissionTime());

    }

}
