package com.apps.exception;

/**
 * Custom Exception defined to inform failure while parsing input text data
 * 
 * @author agautam
 * 
 */
public class InvalidMeetingRequestDataException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5801100515637077009L;

    public InvalidMeetingRequestDataException() {
        super();
    }

    public InvalidMeetingRequestDataException(String message) {
        super(message);
    }

    public InvalidMeetingRequestDataException(String message, Exception e) {
        super(message, e);
    }
}
