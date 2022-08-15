package org.airretailer.exception;

public class NoSeatsFoundException extends RuntimeException{

    private final String message;
    public NoSeatsFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
