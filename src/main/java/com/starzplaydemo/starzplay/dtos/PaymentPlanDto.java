package com.starzplaydemo.starzplay.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentPlanDto {
    private BigDecimal id;
    @JsonIgnore
    private BigDecimal paymentMethodId;
    private Double netAmount;
    private Integer taxAmount;
    private Double grossAmount;
    private String currency;
    private String duration;
}
