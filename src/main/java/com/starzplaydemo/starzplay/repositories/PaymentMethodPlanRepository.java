package com.starzplaydemo.starzplay.repositories;

import com.starzplaydemo.starzplay.entities.PaymentMethodPlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodPlanRepository extends JpaRepository<PaymentMethodPlansEntity, Long> {
}
