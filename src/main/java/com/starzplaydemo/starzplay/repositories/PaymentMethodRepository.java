package com.starzplaydemo.starzplay.repositories;

import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import com.starzplaydemo.starzplay.entities.PaymentMethodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Repository
public class PaymentMethodRepository {
    @Autowired
    private PaymentMethodBaseRepository paymentMethodBaseRepository;

    public PaymentMethodDto findById(BigDecimal id) {
        PaymentMethodEntity paymentMethodEntity = paymentMethodBaseRepository.findById(id).orElse(null);
        if (isNull(paymentMethodEntity)) return null;
        return PaymentMethodDto.builder()
                .id(paymentMethodEntity.getId())
                .name(paymentMethodEntity.getName())
                .displayName(paymentMethodEntity.getDisplayName())
                .paymentType(paymentMethodEntity.getPaymentType())
                .build();
    }

    public PaymentMethodDto findFirstByName(String name) {
        PaymentMethodEntity paymentMethodEntity = paymentMethodBaseRepository.findFirstByName(name).orElse(null);
        if (isNull(paymentMethodEntity)) return null;
        return PaymentMethodDto.builder()
                .id(paymentMethodEntity.getId())
                .name(paymentMethodEntity.getName())
                .displayName(paymentMethodEntity.getDisplayName())
                .paymentType(paymentMethodEntity.getPaymentType())
                .build();
    }

    public List<PaymentMethodDto> findAll() {
        List<PaymentMethodEntity> paymentMethodEntities = paymentMethodBaseRepository.findAll();
        if (CollectionUtils.isEmpty(paymentMethodEntities)) return Collections.emptyList();
        List<PaymentMethodDto> paymentMethods = new ArrayList<>();
        for (PaymentMethodEntity paymentMethodEntity : paymentMethodEntities) {
            paymentMethods.add(PaymentMethodDto.builder()
                    .id(paymentMethodEntity.getId())
                    .name(paymentMethodEntity.getName())
                    .displayName(paymentMethodEntity.getDisplayName())
                    .paymentType(paymentMethodEntity.getPaymentType())
                    .build());
        }
        return paymentMethods;
    }

    public PaymentMethodDto save(PaymentMethodDto paymentMethodDto) {
        if (nonNull(paymentMethodDto.getId())) {
            PaymentMethodEntity paymentMethodEntity = paymentMethodBaseRepository.findById(paymentMethodDto.getId()).orElse(null);
            if (nonNull(paymentMethodEntity)) {
                paymentMethodEntity.save(paymentMethodDto);
                paymentMethodBaseRepository.saveAndFlush(paymentMethodEntity);
            }
            return paymentMethodDto;
        }
        return paymentMethodBaseRepository.save(PaymentMethodEntity.from(paymentMethodDto)).convertToDto();
    }
}
