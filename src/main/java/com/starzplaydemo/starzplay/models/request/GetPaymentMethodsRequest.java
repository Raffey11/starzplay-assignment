package com.starzplaydemo.starzplay.models.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class GetPaymentMethodsRequest {
    private final BigDecimal id;
    private final String name;
}
