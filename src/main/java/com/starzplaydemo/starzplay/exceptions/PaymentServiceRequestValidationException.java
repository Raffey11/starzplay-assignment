package com.starzplaydemo.starzplay.exceptions;

import java.util.Map;

public class PaymentServiceRequestValidationException extends RuntimeException {
    public PaymentServiceRequestValidationException(Map<String, String> errors) {

    }
}
