package com.starzplaydemo.starzplay.entities;

import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import com.starzplaydemo.starzplay.dtos.PaymentPlanDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment_methods")
public class PaymentMethodEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(nullable = false)
    private String paymentType;

    public static PaymentMethodEntity from(PaymentMethodDto paymentMethodDto) {
        return PaymentMethodEntity.builder()
                .id(paymentMethodDto.getId())
                .name(paymentMethodDto.getName())
                .displayName(paymentMethodDto.getDisplayName())
                .paymentType(paymentMethodDto.getPaymentType())
                .build();
    }

    public void save(PaymentMethodDto paymentMethodDto) {
        this.setId(paymentMethodDto.getId());
        this.setName(paymentMethodDto.getName());
        this.setDisplayName(paymentMethodDto.getDisplayName());
        this.setPaymentType(paymentMethodDto.getPaymentType());
    }

    public PaymentMethodDto convertToDto() {
        return PaymentMethodDto.builder()
                .id(this.getId())
                .name(this.getName())
                .displayName(this.getDisplayName())
                .paymentType(this.getPaymentType())
                .build();
    }

    private List<PaymentPlanDto> convertToPaymentPlanDto(Set<PaymentPlanEntity> paymentPlans) {
        return paymentPlans.stream().map(paymentPlansEntity -> PaymentPlanDto.builder()
                .id(paymentPlansEntity.getId())
                .netAmount(paymentPlansEntity.getNetAmount())
                .taxAmount(paymentPlansEntity.getTaxAmount())
                .grossAmount(paymentPlansEntity.getGrossAmount())
                .currency(paymentPlansEntity.getCurrency())
                .duration(paymentPlansEntity.getDuration())
                .build()).collect(Collectors.toList());
    }
}
