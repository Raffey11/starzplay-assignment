package com.starzplaydemo.starzplay.exceptions;

public class PaymentServiceException extends RuntimeException {
    public PaymentServiceException(String message) {
        super(message);
    }
}
