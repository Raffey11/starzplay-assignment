package com.starzplaydemo.starzplay.entities;

import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import com.starzplaydemo.starzplay.dtos.PaymentPlanDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment_plans")
public class PaymentPlanEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

//    @ManyToOne
//    @JoinColumn(name = "payment_method_id", nullable = false)
//    private PaymentMethodEntity paymentMethod;

    @Column(name = "payment_method_id", nullable = false)
    private BigDecimal paymentMethodId;

    @Column(name = "net_amount", nullable = false)
    private Double netAmount;

    @Column(name = "tax_amount", nullable = false)
    private Integer taxAmount;

    @Column(name = "gross_amount", nullable = false)
    private Double grossAmount;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "duration", nullable = false)
    private String duration;

    public static PaymentPlanEntity from(PaymentPlanDto paymentPlanDto) {
        return PaymentPlanEntity.builder()
                .paymentMethodId(paymentPlanDto.getPaymentMethodId())
                .netAmount(paymentPlanDto.getNetAmount())
                .grossAmount(paymentPlanDto.getGrossAmount())
                .taxAmount(paymentPlanDto.getTaxAmount())
                .currency(paymentPlanDto.getCurrency())
                .duration(paymentPlanDto.getDuration())
                .build();
    }

    public void save(PaymentPlanDto paymentPlanDto) {
        this.setId(paymentPlanDto.getId());
        this.setNetAmount(paymentPlanDto.getNetAmount());
        this.setGrossAmount(paymentPlanDto.getGrossAmount());
        this.setTaxAmount(paymentPlanDto.getTaxAmount());
        this.setCurrency(paymentPlanDto.getCurrency());
        this.setDuration(paymentPlanDto.getDuration());
    }

    public PaymentPlanDto convertToDto() {
        return PaymentPlanDto.builder()
                .id(this.getId())
                .netAmount(this.getNetAmount())
                .grossAmount(this.getGrossAmount())
                .taxAmount(this.getTaxAmount())
                .currency(this.getCurrency())
                .duration(this.getDuration())
                .paymentMethodId(this.getPaymentMethodId())
                .build();
    }
}
