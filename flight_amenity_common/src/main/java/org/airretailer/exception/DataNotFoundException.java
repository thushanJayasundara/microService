package org.airretailer.exception;

public class DataNotFoundException extends RuntimeException {


    private final String message;
    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}