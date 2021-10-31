package com.starzplaydemo.starzplay.services;

import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import com.starzplaydemo.starzplay.dtos.PaymentPlanDto;
import com.starzplaydemo.starzplay.exceptions.PaymentServiceException;
import com.starzplaydemo.starzplay.models.request.CreatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.models.request.GetPaymentMethodsRequest;
import com.starzplaydemo.starzplay.models.request.UpdatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.models.response.*;
import com.starzplaydemo.starzplay.repositories.PaymentMethodRepository;
import com.starzplaydemo.starzplay.repositories.PaymentPlanRepository;
import com.starzplaydemo.starzplay.utils.constants.Errors;
import com.starzplaydemo.starzplay.utils.factories.ApiResponseBuilderFactory;
import com.starzplaydemo.starzplay.utils.validators.PaymentApiValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentPlanRepository paymentPlanRepository;

    @Autowired
    public PaymentServiceImpl(PaymentMethodRepository paymentMethodRepository, PaymentPlanRepository paymentPlanRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentPlanRepository = paymentPlanRepository;
    }

    @Override
    public ApiResponse getPaymentMethods(GetPaymentMethodsRequest request) {
        if (nonNull(request.getId())) {
            PaymentMethodDto paymentMethod = getPaymentMethodById(request.getId());
            if (nonNull(paymentMethod)) {
                paymentMethod.setPaymentPlans(paymentPlanRepository.findAllByPaymentMethodId(paymentMethod.getId()));
                return ApiResponseBuilderFactory.buildSuccessGetPaymentMethodsApiResponse(Collections.singletonList(paymentMethod));
            }
            return ApiResponseBuilderFactory.buildNotFoundGetPaymentMethodsApiResponse(Collections.emptyList());
        } else if (!StringUtils.isEmpty(request.getName())) {
            PaymentMethodDto paymentMethod = getPaymentMethodByName(request.getName());
            if (nonNull(paymentMethod)) {
                paymentMethod.setPaymentPlans(paymentPlanRepository.findAllByPaymentMethodId(paymentMethod.getId()));
                return ApiResponseBuilderFactory.buildSuccessGetPaymentMethodsApiResponse(Collections.singletonList(paymentMethod));
            }
            return ApiResponseBuilderFactory.buildNotFoundGetPaymentMethodsApiResponse(Collections.emptyList());
        }
        return getAllPaymentMethods();
    }

    @Override
    public ApiResponse getAllPaymentMethods() {
        List<PaymentMethodDto> paymentMethods = paymentMethodRepository.findAll();
        if (CollectionUtils.isEmpty(paymentMethods))
            return ApiResponseBuilderFactory.buildNotFoundGetPaymentMethodsApiResponse(Collections.emptyList());
        for (PaymentMethodDto paymentMethodDto : paymentMethods) {
            paymentMethodDto.setPaymentPlans(paymentPlanRepository.findAllByPaymentMethodId(paymentMethodDto.getId()));
        }
        return ApiResponseBuilderFactory.buildSuccessGetPaymentMethodsApiResponse(paymentMethods);
    }

    @Override
    public PaymentMethodDto getPaymentMethodById(BigDecimal id) {
        return paymentMethodRepository.findById(id);
    }

    @Override
    public PaymentMethodDto getPaymentMethodByName(String name) {
        return paymentMethodRepository.findFirstByName(name);
    }

    @Override
    public BaseResponse createPaymentMethod(CreatePaymentMethodApiRequest request) {
        Map<String, String> errors = PaymentApiValidator.validateCreatePaymentMethodApiRequest((request));
        if (!errors.isEmpty()) return ApiResponseBuilderFactory.buildValidationErrorResponse(errors);
        createPaymentEntity(request);
        return ApiResponseBuilderFactory.buildSuccessCreatePaymentMethodsApiResponse();
    }

    private void createPaymentEntity(CreatePaymentMethodApiRequest request) {
        PaymentMethodDto paymentMethodDto = paymentMethodRepository.save(PaymentMethodDto.builder()
                .name(request.getName())
                .displayName(request.getDisplayName())
                .paymentType(request.getPaymentType())
                .build());
        if (!CollectionUtils.isEmpty(request.getPaymentPlans())) {
            for (PaymentPlanDto paymentPlanDto : request.getPaymentPlans()) {
                createPaymentPlan(paymentPlanDto, paymentMethodDto);
            }
        }
    }

    @Override
    public BaseResponse updatePaymentMethod(BigDecimal id, UpdatePaymentMethodApiRequest request) {
        if (isNull(id)) throw new PaymentServiceException(Errors.EMPTY_ID);
        PaymentMethodDto paymentMethodDto = paymentMethodRepository.findById(id);
        if (isNull(paymentMethodDto)) return ApiResponseBuilderFactory.buildNotFoundUpdatePaymentMethodApiResponse();
        Map<String, String> errors = PaymentApiValidator.validateUpdatePaymentMethodApiRequest(id, request);
        if (!errors.isEmpty()) return ApiResponseBuilderFactory.buildValidationErrorResponse(errors);
        updatePaymentEntity(request, paymentMethodDto);
        return ApiResponseBuilderFactory.buildSuccessUpdatePaymentMethodsApiResponse();
    }

    private void updatePaymentEntity(UpdatePaymentMethodApiRequest request, PaymentMethodDto paymentMethodDto) {
        if (nonNull(request.getName())) paymentMethodDto.setName(request.getName());
        if (nonNull(request.getDisplayName())) paymentMethodDto.setDisplayName(request.getDisplayName());
        if (nonNull(request.getPaymentType())) paymentMethodDto.setPaymentType(request.getPaymentType());
        if (CollectionUtils.isEmpty(request.getPaymentPlans())) paymentMethodRepository.save(paymentMethodDto);
        for (PaymentPlanDto paymentPlanDto : request.getPaymentPlans()) {
            updateOrCreatePaymentPlan(paymentPlanDto, paymentMethodDto);
        }
        paymentMethodRepository.save(paymentMethodDto);
    }

    private void createPaymentPlan(PaymentPlanDto paymentPlanDto, PaymentMethodDto paymentMethodDto) {
        if (isNull(paymentPlanDto.getId())) {
            boolean areAllFieldsPresent = areAllFieldsPresentInPaymentPlan(paymentPlanDto);
            if (areAllFieldsPresent) {
                paymentPlanRepository.save(createNewPaymentPlan(paymentPlanDto, paymentMethodDto));
            } else {
                throw new PaymentServiceException(Errors.MISSING_FIELDS_FOR_PAYMENT_PLAN_CREATE);
            }
        }
    }

    private void updateOrCreatePaymentPlan(PaymentPlanDto paymentPlanDto, PaymentMethodDto paymentMethodDto) {
        if (isNull(paymentPlanDto.getId())) {
            boolean areAllFieldsPresent = areAllFieldsPresentInPaymentPlan(paymentPlanDto);
            if (areAllFieldsPresent) {
                paymentPlanRepository.save(createNewPaymentPlan(paymentPlanDto, paymentMethodDto));
            } else {
                throw new PaymentServiceException(Errors.MISSING_FIELDS_FOR_PAYMENT_PLAN_CREATE);
            }
        } else {
            //TODO: check for duplicate payment plans
            boolean isAtLeastOneFieldPresent = isAtLeastOneFieldPresentInPaymentPlanForUpdate(paymentPlanDto);
            if (isAtLeastOneFieldPresent) {
                boolean isThePaymentPlanPartOfThePaymentMethod = isThePaymentPlanAssociatedWithThePaymentMethod(paymentPlanDto, paymentMethodDto);
                if (!isThePaymentPlanPartOfThePaymentMethod) throw new PaymentServiceException(Errors.PAYMENT_PLAN_DOES_NOT_BELONG_TO_THE_PAYMENT_METHOD + ", Payment Plan ID: " + paymentPlanDto.getId());
                PaymentPlanDto paymentPlan = paymentPlanRepository.findById(paymentPlanDto.getId());
                if (isNull(paymentPlan))
                    throw new PaymentServiceException(Errors.PAYMENT_PLAN_NOT_FOUND + " for ID: " + paymentPlanDto.getId());
                updatePaymentPlanEntity(paymentPlan, paymentPlanDto);
            } else {
                throw new PaymentServiceException(Errors.MISSING_FIELDS_FOR_PAYMENT_PLAN_UPDATE + " for ID: " + paymentPlanDto.getId());
            }
        }
    }

    private boolean isThePaymentPlanAssociatedWithThePaymentMethod(PaymentPlanDto paymentPlanDto, PaymentMethodDto paymentMethodDto) {
        List<PaymentPlanDto> paymentPlans = paymentPlanRepository.findAllByPaymentMethodId(paymentMethodDto.getId());
        for (PaymentPlanDto existingPaymentPlan : paymentPlans) {
            if (existingPaymentPlan.getId().equals(paymentPlanDto.getId())) return true;
        }
        return false;
    }

    private boolean isAtLeastOneFieldPresentInPaymentPlanForUpdate(PaymentPlanDto paymentPlanDto) {
        return nonNull(paymentPlanDto.getNetAmount()) ||
                nonNull(paymentPlanDto.getGrossAmount()) ||
                nonNull(paymentPlanDto.getTaxAmount()) ||
                nonNull(paymentPlanDto.getCurrency()) ||
                nonNull(paymentPlanDto.getDuration());
    }

    private boolean areAllFieldsPresentInPaymentPlan(PaymentPlanDto paymentPlanDto) {
        return nonNull(paymentPlanDto.getNetAmount()) &&
                nonNull(paymentPlanDto.getGrossAmount()) &&
                nonNull(paymentPlanDto.getTaxAmount()) &&
                nonNull(paymentPlanDto.getCurrency()) &&
                nonNull(paymentPlanDto.getDuration());
    }

//    private boolean areAllFieldsPresentForPaymentMethodCreation(PaymentMethodDto paymentMethodDto) {
//        boolean areAllFieldsPresentForPaymentMethodCreation = !StringUtils.isEmpty(paymentMethodDto.getName()) &&
//                !StringUtils.isEmpty(paymentMethodDto.getDisplayName()) &&
//                !StringUtils.isEmpty(paymentMethodDto.getPaymentType());
//
//        boolean areAnyPaymentPlansPresentAndIfSoAreAllItsFieldsPresent;
//
//        if (CollectionUtils.isEmpty(paymentMethodDto.getPaymentPlans())) {
//            for (PaymentPlanDto paymentPlanDto : paymentMethodDto.getPaymentPlans()) {
//                areAllFieldsPresentInPaymentPlan(paymentPlanDto);
//            }
//        }
//
//    }

    private PaymentPlanDto createNewPaymentPlan(PaymentPlanDto request, PaymentMethodDto paymentMethodDto) {
        return PaymentPlanDto.builder()
                .netAmount(request.getNetAmount())
                .grossAmount(request.getGrossAmount())
                .taxAmount(request.getTaxAmount())
                .currency(request.getCurrency())
                .duration(request.getDuration())
                .paymentMethodId(paymentMethodDto.getId())
                .build();
    }

    private void updatePaymentPlanEntity(PaymentPlanDto oldPaymentPlan, PaymentPlanDto newPaymentPlan) {
        if (nonNull(newPaymentPlan.getNetAmount())) oldPaymentPlan.setNetAmount(newPaymentPlan.getNetAmount());
        if (nonNull(newPaymentPlan.getGrossAmount())) oldPaymentPlan.setGrossAmount(newPaymentPlan.getGrossAmount());
        if (nonNull(newPaymentPlan.getTaxAmount())) oldPaymentPlan.setTaxAmount(newPaymentPlan.getTaxAmount());
        if (nonNull(newPaymentPlan.getCurrency())) oldPaymentPlan.setCurrency(newPaymentPlan.getCurrency());
        if (nonNull(newPaymentPlan.getDuration())) oldPaymentPlan.setDuration(newPaymentPlan.getDuration());
        paymentPlanRepository.save(oldPaymentPlan);
    }
}
