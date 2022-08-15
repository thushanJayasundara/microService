package org.airretailer.exception;

public class MandatoryFieldException extends  RuntimeException{

    private final String message;

    public MandatoryFieldException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}