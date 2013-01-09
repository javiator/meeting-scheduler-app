package com.apps;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MeetingSchedulerIT {

    @Test
    public void testDefaultApp() {

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        MeetingSchedulerApp.main(new String[] {}); // running the program in
                                                   // default mode using dummy
                                                   // input fetcher having data
                                                   // as per given text in
                                                   // Exercise

        System.out.flush();
        System.setOut(old);

        String output = baos.toString();
        // verifying that correctØ output has been printed by the application
        Assert.assertEquals(true, output.contains("******************OUTPUT******************"));
        Assert.assertEquals(true, output.contains("2011-03-21"));
        Assert.assertEquals(true, output.contains("09:00 11:00 EMP001"));
        Assert.assertEquals(true, output.contains("2011-03-22"));
        Assert.assertEquals(true, output.contains("14:00 16:00 EMP003"));
        Assert.assertEquals(true, output.contains("16:00 17:00 EMP004"));

    }

    @Test
    @Ignore
    public void testConsoleInputDataSet1() throws InterruptedException {

        String dummyInputText = "adfadfafadfafas0900 1730\r\n" + "2011-03-17 10:17:06 EMP001\r\n"
                + "2011-03-21 09:00 2\r\n" + "2011-03-16 12:34:56 EMP002\r\n" + "2011-03-21 09:00 2\r\n"
                + "2011-03-16 09:28:23 EMP003\r\n" + "2011-03-22 14:00 2\r\n" + "2011-03-17 10:17:06 EMP004\r\n"
                + "2011-03-22 16:00 1\r\n" + "2011-03-15 17:29:12 EMP005\r\n" + "2011-03-21 16:00 3\r\n"
                + "2011-03-15 17:29:12 EMP115\r\n" + "2011-03-21 16:00 1\r\n" + "2011-03-30 17:29:12 EMP200\r\n"
                + "2011-03-20 12:00 2\r\n" + "2011-03-15 17:29:12 EMP005\r\n" + "2011-03-15 14:00 1\r\n"
                + "2011-03-23 17:29:12 EMP005\r\n" + "2011-03-23 10:00 3\r\n done";

    }

}
