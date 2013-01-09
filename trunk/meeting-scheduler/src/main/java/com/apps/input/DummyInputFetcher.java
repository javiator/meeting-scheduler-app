package com.apps.input;

/**
 * Dummy data provider for easy testing.
 * 
 * @author agautam
 * 
 */
public class DummyInputFetcher implements InputFetcher {

    /**
     * Sample raw meeting schedule data if user don't want to provide input and
     * use some existing example data.
     */
    private static final String dummyInputText = "0900 1730\r\n" + "2011-03-17 10:17:06 EMP001\r\n"
            + "2011-03-21 09:00 2\r\n" + "2011-03-16 12:34:56 EMP002\r\n" + "2011-03-21 09:00 2\r\n"
            + "2011-03-16 09:28:23 EMP003\r\n" + "2011-03-22 14:00 2\r\n" + "2011-03-17 10:17:06 EMP004\r\n"
            + "2011-03-22 16:00 1\r\n" + "2011-03-15 17:29:12 EMP005\r\n" + "2011-03-21 16:00 3\r\n";

    /**
     * Method to fetch input dummy data.
     * 
     */
    @Override
    public String getInputData() {

        return dummyInputText;

    }

}
