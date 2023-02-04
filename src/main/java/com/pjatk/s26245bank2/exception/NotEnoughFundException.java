package com.pjatk.s26245bank2.exception;

public class NotEnoughFundException extends RuntimeException{
    public NotEnoughFundException(String message) {
        super(message);
    }
}
