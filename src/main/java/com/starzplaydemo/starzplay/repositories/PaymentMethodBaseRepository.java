package com.starzplaydemo.starzplay.repositories;

import com.starzplaydemo.starzplay.entities.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PaymentMethodBaseRepository extends JpaRepository<PaymentMethodEntity, BigDecimal> {
    Optional<PaymentMethodEntity> findFirstByName(String name);
}
