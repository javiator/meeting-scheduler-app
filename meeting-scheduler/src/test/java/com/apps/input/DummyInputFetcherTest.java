package com.apps.input;

import junit.framework.Assert;

import org.junit.Test;

public class DummyInputFetcherTest {

    private static final String dummyInputText = "0900 1730\r\n" + "2011-03-17 10:17:06 EMP001\r\n"
            + "2011-03-21 09:00 2\r\n" + "2011-03-16 12:34:56 EMP002\r\n" + "2011-03-21 09:00 2\r\n"
            + "2011-03-16 09:28:23 EMP003\r\n" + "2011-03-22 14:00 2\r\n" + "2011-03-17 10:17:06 EMP004\r\n"
            + "2011-03-22 16:00 1\r\n" + "2011-03-15 17:29:12 EMP005\r\n" + "2011-03-21 16:00 3\r\n";

    @Test
    public void test() {

        InputFetcher dummyInputFetcher = new DummyInputFetcher();

        Assert.assertEquals(dummyInputText, dummyInputFetcher.getInputData());

    }

}
