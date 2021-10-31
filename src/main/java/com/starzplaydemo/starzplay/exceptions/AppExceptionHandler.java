package com.starzplaydemo.starzplay.exceptions;

import com.starzplaydemo.starzplay.models.response.ApiResponse;
import com.starzplaydemo.starzplay.models.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLDataException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PaymentServiceException.class})
    public ResponseEntity<Object> handlePaymentServiceException(PaymentServiceException ex, WebRequest request) {
        String errorMessageDetail = ex.getLocalizedMessage();
        if (StringUtils.isEmpty(errorMessageDetail)) errorMessageDetail = ex.toString();
        ApiResponse response = ApiResponse.builder()
                .status(400)
                .message("ERROR")
                .body(ErrorMessage.builder()
                        .errors(errorMessageDetail)
                        .build())
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SQLDataException.class})
    public ResponseEntity<Object> handlePaymentServiceException(SQLDataException ex, WebRequest request) {
        String errorMessageDetail = ex.getLocalizedMessage();
        if (StringUtils.isEmpty(errorMessageDetail)) errorMessageDetail = ex.toString();
        ApiResponse response = ApiResponse.builder()
                .status(400)
                .message("ERROR")
                .body(ErrorMessage.builder()
                        .errors(errorMessageDetail)
                        .build())
                .build();
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
