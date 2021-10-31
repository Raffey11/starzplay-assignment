package com.starzplaydemo.starzplay.services;

import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import com.starzplaydemo.starzplay.entities.PaymentMethodEntity;
import com.starzplaydemo.starzplay.models.request.CreatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.models.request.GetPaymentMethodsRequest;
import com.starzplaydemo.starzplay.models.request.UpdatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.models.response.ApiResponse;
import com.starzplaydemo.starzplay.models.response.BaseResponse;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    ApiResponse getPaymentMethods(GetPaymentMethodsRequest request);
    ApiResponse getAllPaymentMethods();
    PaymentMethodDto getPaymentMethodById(BigDecimal id);
    PaymentMethodDto getPaymentMethodByName(String name);
    BaseResponse createPaymentMethod(CreatePaymentMethodApiRequest request);
    BaseResponse updatePaymentMethod(BigDecimal id, UpdatePaymentMethodApiRequest request);
}
