package com.starzplaydemo.starzplay.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "payment_method_plans")
public class PaymentMethodPlansEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    @Column(name = "payment_method_id", nullable = false)
    private BigDecimal paymentMethodId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "payment_plan_id", referencedColumnName="id", nullable = false)
    private PaymentPlanEntity paymentPlan;
}
