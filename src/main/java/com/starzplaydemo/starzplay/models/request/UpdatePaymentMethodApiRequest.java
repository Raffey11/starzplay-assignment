package com.starzplaydemo.starzplay.models.request;

import com.starzplaydemo.starzplay.dtos.PaymentPlanDto;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class UpdatePaymentMethodApiRequest {
    private final BigDecimal id;
    private final String name;
    private final String displayName;
    private final String paymentType;
    private final List<PaymentPlanDto> paymentPlans;
}
