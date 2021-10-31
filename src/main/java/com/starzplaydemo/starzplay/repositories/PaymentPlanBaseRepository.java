package com.starzplaydemo.starzplay.repositories;

import com.starzplaydemo.starzplay.entities.PaymentPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentPlanBaseRepository extends JpaRepository<PaymentPlanEntity, BigDecimal> {
    List<PaymentPlanEntity> findAllByPaymentMethodId(BigDecimal paymentMethodId);
}
