package com.starzplaydemo.starzplay.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PaymentMethodDto {
    @JsonIgnore
    private BigDecimal id;
    private String name;
    private String displayName;
    private String paymentType;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PaymentPlanDto> paymentPlans = new ArrayList<>();
}
