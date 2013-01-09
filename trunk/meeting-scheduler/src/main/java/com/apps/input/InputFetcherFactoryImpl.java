package com.apps.input;

/**
 * Interface to provide different kind of InputFetcher objects based on the
 * input value
 * 
 * @author agautam
 * 
 */
public class InputFetcherFactoryImpl implements InputFetcherFactory {

    /**
     * Method to return different InputFetcher based on input value for 0 - it
     * returns DummyInputFetcher for 1 - it returns ConsoleInputFetcher for any
     * other value - it returns DummyInputFetcher
     * 
     * @param type
     *            int
     * @return InputFetcher
     */
    public InputFetcher getInputFetcher(int type) {

        InputFetcher inputFetcher;
        switch (type) {
        case 0:
            inputFetcher = new DummyInputFetcher();
            break;
        case 1:
            inputFetcher = new ConsoleInputFetcher();
            break;
        default:
            inputFetcher = new DummyInputFetcher();
        }

        return inputFetcher;
    }
}
