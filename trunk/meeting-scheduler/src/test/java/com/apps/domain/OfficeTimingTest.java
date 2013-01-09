package com.apps.domain;

import junit.framework.Assert;

import org.junit.Test;

public class OfficeTimingTest {

    private long officeStartTime = 32400000;

    private long officeEndTime = 63000000;

    @Test
    public void testDefaultOfficeTimings() {

        OfficeTiming officeTiming = new OfficeTiming();
        Assert.assertEquals(officeStartTime, officeTiming.getOfficeStartTime());
        Assert.assertEquals(officeEndTime, officeTiming.getOfficeEndTime());
    }

    @Test
    public void testOfficeTimings() {

        OfficeTiming officeTiming = new OfficeTiming();
        officeTiming.setOfficeStartTime(1000);
        officeTiming.setOfficeEndTime(2000);
        Assert.assertEquals(1000, officeTiming.getOfficeStartTime());
        Assert.assertEquals(2000, officeTiming.getOfficeEndTime());
    }

}
