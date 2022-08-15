package org.airretailer.exception;

public class DuplicationErrorException extends RuntimeException {

    private final String message;
    public DuplicationErrorException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}