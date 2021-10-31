package com.starzplaydemo.starzplay.utils.factories;

import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import com.starzplaydemo.starzplay.models.response.ApiErrorResponse;
import com.starzplaydemo.starzplay.models.response.ApiResponse;
import com.starzplaydemo.starzplay.models.response.ErrorResponseBody;
import com.starzplaydemo.starzplay.models.response.GetPaymentMethodsResponse;
import com.starzplaydemo.starzplay.utils.constants.ApiStatus;
import com.starzplaydemo.starzplay.utils.constants.Errors;

import java.util.List;
import java.util.Map;

public class ApiResponseBuilderFactory {
    public static ApiErrorResponse buildValidationErrorResponse(Map<String , String> errors) {
        return ApiErrorResponse.builder()
                .status(ApiStatus.BAD_REQUEST)
                .message(Errors.BAD_REQUEST)
                .body(ErrorResponseBody.builder()
                        .errors(errors)
                        .build())
                .build();
    }

    public static ApiResponse buildSuccessGetPaymentMethodsApiResponse(List<PaymentMethodDto> paymentMethods) {
        return ApiResponse.builder()
                .status(ApiStatus.SUCCESS)
                .message("SUCCESS")
                .body(GetPaymentMethodsResponse.builder()
                        .paymentMethods(paymentMethods)
                        .build())
                .build();
    }

    public static ApiResponse buildSuccessUpdatePaymentMethodsApiResponse() {
        return ApiResponse.builder()
                .status(ApiStatus.SUCCESS)
                .message("SUCCESS")
                .body(null)
                .build();
    }

    public static ApiResponse buildSuccessCreatePaymentMethodsApiResponse() {
        return ApiResponse.builder()
                .status(ApiStatus.SUCCESS)
                .message("SUCCESS")
                .body(null)
                .build();
    }

    public static ApiResponse buildNotFoundGetPaymentMethodsApiResponse(List<PaymentMethodDto> paymentMethods) {
        return ApiResponse.builder()
                .status(ApiStatus.NOT_FOUND)
                .message("NOT FOUND")
                .body(GetPaymentMethodsResponse.builder()
                        .paymentMethods(paymentMethods)
                        .build())
                .build();
    }

    public static ApiResponse buildNotFoundUpdatePaymentMethodApiResponse() {
        return ApiResponse.builder()
                .status(ApiStatus.NOT_FOUND)
                .message("NOT FOUND")
                .body(null)
                .build();
    }
}
