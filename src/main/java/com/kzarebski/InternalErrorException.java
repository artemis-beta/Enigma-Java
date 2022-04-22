package com.kzarebski;

public class InternalErrorException extends Exception {
    public InternalErrorException(String errorMessage) {
        super(errorMessage);
    }
}
