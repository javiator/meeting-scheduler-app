package com.apps.exception;

import junit.framework.Assert;

import org.junit.Test;

public class InvalidMeetingRequestDataExceptionTest {

    @Test
    public void testDefaultConstrutor() {

        InvalidMeetingRequestDataException e = new InvalidMeetingRequestDataException();

        Assert.assertEquals(null, e.getMessage());
        Assert.assertEquals(null, e.getCause());
    }

    @Test
    public void testConstructorWithStringParameter() {

        InvalidMeetingRequestDataException e = new InvalidMeetingRequestDataException("Exception Details");

        Assert.assertEquals("Exception Details", e.getMessage());
        Assert.assertEquals(null, e.getCause());
    }

    @Test
    public void testConstructorWithStringAndExceptionParameter() {

        InvalidMeetingRequestDataException e = new InvalidMeetingRequestDataException("Exception Details",
                new Exception("Message"));

        Assert.assertEquals("Exception Details", e.getMessage());
        Assert.assertEquals("Message", e.getCause().getMessage());
    }

}
