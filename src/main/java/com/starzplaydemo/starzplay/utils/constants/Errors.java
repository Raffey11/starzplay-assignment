package com.starzplaydemo.starzplay.utils.constants;

public class Errors {
    public static final String BAD_REQUEST = "BAD REQUEST";
    public static final String EMPTY_ID = "Please provide Payment Method ID to update";
    public static final String PAYMENT_METHOD_NOT_FOUND = "Payment Method Not Found";
    public static final String PAYMENT_PLAN_NOT_FOUND = "Payment Plan Not Found";
    public static final String PAYMENT_PLAN_DOES_NOT_BELONG_TO_THE_PAYMENT_METHOD = "The Payment Plan does not belong to the payment method";
    public static final String EMPTY_NAME_FIELD = "Name field cannot be empty";
    public static final String EMPTY_DISPLAY_NAME_FIELD = "Display Name field cannot be empty";
    public static final String EMPTY_PAYMENT_TYPE_FIELD = "Payment Type field cannot be empty";
    public static final String EMPTY_NET_AMOUNT_FIELD = "Net Amount field cannot be empty";
    public static final String EMPTY_GROSS_AMOUNT_FIELD = "Gross Amount field cannot be empty";
    public static final String EMPTY_TAX_AMOUNT_FIELD = "Tax Amount field cannot be empty";
    public static final String EMPTY_DURATION_FIELD = "Duration field cannot be empty";
    public static final String EMPTY_CURRENCY_FIELD = "Currency field cannot be empty";
    public static final String INVALID_DURATION_FIELD_LENGTH = "Payment Plan Duration field length should be in between 3 - 255";
    public static final String INVALID_CURRENCY_FIELD_LENGTH = "Payment Plan Currency field length should be in between 3 - 255";
    public static final String INVALID_NAME_LENGTH = "Name field length should be in between 3 - 255";
    public static final String INVALID_DISPLAY_NAME_LENGTH = "Display Name field length should be in between 3 - 255";
    public static final String INVALID_PAYMENT_TYPE_LENGTH = "Payment Type field length should be in between 3 - 255";
    public static final String MISSING_FIELDS_FOR_PAYMENT_PLAN_CREATE = "Please fill all payment plan fields to create a new one";
    public static final String MISSING_FIELDS_FOR_PAYMENT_PLAN_UPDATE = "Some fields are missing for payment plan update";
    public static final String ERROR_CREATING_NEW_PAYMENT_PLAN = "There was an error creating a new payment plan";
}
