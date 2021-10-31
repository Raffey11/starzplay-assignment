package com.starzplaydemo.starzplay.repositories;

import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import com.starzplaydemo.starzplay.dtos.PaymentPlanDto;
import com.starzplaydemo.starzplay.entities.PaymentMethodEntity;
import com.starzplaydemo.starzplay.entities.PaymentPlanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Repository
public class PaymentPlanRepository {
    @Autowired
    private PaymentPlanBaseRepository paymentPlanBaseRepository;

    public List<PaymentPlanDto> findAllByPaymentMethodId(BigDecimal paymentMethodId) {
        List<PaymentPlanEntity> paymentPlanEntities = paymentPlanBaseRepository.findAllByPaymentMethodId(paymentMethodId);
        if (CollectionUtils.isEmpty(paymentPlanEntities)) return Collections.emptyList();
        List<PaymentPlanDto> paymentPlans = new ArrayList<>();
        for (PaymentPlanEntity paymentPlanEntity : paymentPlanEntities) {
            paymentPlans.add(paymentPlanEntity.convertToDto());
        }
        return paymentPlans;
    }

    public PaymentPlanDto findById(BigDecimal id) {
        PaymentPlanEntity paymentPlanEntity = paymentPlanBaseRepository.findById(id).orElse(null);
        if (isNull(paymentPlanEntity)) return null;
        return paymentPlanEntity.convertToDto();
    }

    public PaymentPlanDto save(PaymentPlanDto paymentPlanDto) {
        if (nonNull(paymentPlanDto.getId())) {
            PaymentPlanEntity paymentPlanEntity = paymentPlanBaseRepository.findById(paymentPlanDto.getId()).orElse(null);
            if (nonNull(paymentPlanEntity)) {
                paymentPlanEntity.save(paymentPlanDto);
                paymentPlanBaseRepository.saveAndFlush(paymentPlanEntity);
            }
            return paymentPlanDto;
        }
        return paymentPlanBaseRepository.save(PaymentPlanEntity.from(paymentPlanDto)).convertToDto();
    }
}
