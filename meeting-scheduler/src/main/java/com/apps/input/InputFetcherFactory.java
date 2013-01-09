package com.apps.input;

/**
 * Interface to provide different kind of InputFetcher objects based on the
 * input value
 * 
 * @author agautam
 * 
 */
public interface InputFetcherFactory {

    /**
     * Method to return different InputFetcher based on input value
     * 
     * @param type
     *            int
     * @return InputFetcher
     */
    InputFetcher getInputFetcher(int type);

}
