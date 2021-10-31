package com.starzplaydemo.starzplay.dtos;

import com.starzplaydemo.starzplay.entities.PaymentPlanEntity;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentMethodPlanDto {
    private BigDecimal id;
    private BigDecimal paymentMethodId;
    private PaymentPlanEntity paymentPlan;
}
