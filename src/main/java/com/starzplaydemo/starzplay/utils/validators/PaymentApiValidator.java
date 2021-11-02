package com.starzplaydemo.starzplay.utils.validators;

import com.starzplaydemo.starzplay.dtos.PaymentPlanDto;
import com.starzplaydemo.starzplay.models.request.CreatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.models.request.UpdatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.utils.constants.Constants;
import com.starzplaydemo.starzplay.utils.constants.Errors;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class PaymentApiValidator {

    public static Map<String, String> validateCreatePaymentMethodApiRequest(CreatePaymentMethodApiRequest request) {
        Map<String, String> errors = new HashMap<>();
        if (StringUtils.isEmpty(request.getName())) errors.put("name", Errors.EMPTY_NAME_FIELD);
        if (request.getName().length() > Constants.MAX_LENGTH || request.getName().length() < Constants.MIN_LENGTH) errors.put("name", Errors.INVALID_NAME_LENGTH);
        if (StringUtils.isEmpty(request.getDisplayName())) errors.put("displayName", Errors.EMPTY_DISPLAY_NAME_FIELD);
        if (request.getName().length() > Constants.MAX_LENGTH || request.getName().length() < Constants.MIN_LENGTH) errors.put("displayName", Errors.INVALID_DISPLAY_NAME_LENGTH);
        if (StringUtils.isEmpty(request.getPaymentType())) errors.put("paymentType", Errors.EMPTY_PAYMENT_TYPE_FIELD);
        if (request.getName().length() > Constants.MAX_LENGTH || request.getName().length() < Constants.MIN_LENGTH) errors.put("paymentType", Errors.INVALID_PAYMENT_TYPE_LENGTH);
        if (CollectionUtils.isEmpty(request.getPaymentPlans())) return errors;
        for (PaymentPlanDto paymentPlanDto : request.getPaymentPlans()) {
            validateCreatePaymentMethodApiPaymentPlan(paymentPlanDto, errors);
        }
        return errors;
    }

    private static void validateCreatePaymentMethodApiPaymentPlan(PaymentPlanDto paymentPlan, Map<String, String> errors) {
        if (isNull(paymentPlan.getNetAmount())) errors.put("paymentPlan.netAmount", Errors.EMPTY_NET_AMOUNT_FIELD);
        if (isNull(paymentPlan.getGrossAmount())) errors.put("paymentPlan.grossAmount", Errors.EMPTY_GROSS_AMOUNT_FIELD);
        if (isNull(paymentPlan.getTaxAmount())) errors.put("paymentPlan.taxAmount", Errors.EMPTY_TAX_AMOUNT_FIELD);
        if (isNull(paymentPlan.getDuration())) errors.put("paymentPlan.duration", Errors.EMPTY_DURATION_FIELD);
        if (paymentPlan.getDuration().length() > Constants.MAX_LENGTH || paymentPlan.getDuration().length() < Constants.MIN_LENGTH) errors.put("paymentPlan.duration", Errors.INVALID_DURATION_FIELD_LENGTH);
        if (isNull(paymentPlan.getCurrency())) errors.put("paymentPlan.currency", Errors.EMPTY_CURRENCY_FIELD);
        if (paymentPlan.getCurrency().length() > Constants.MAX_LENGTH || paymentPlan.getCurrency().length() < Constants.MIN_LENGTH) errors.put("paymentPlan.currency", Errors.INVALID_CURRENCY_FIELD_LENGTH);
    }

    public static Map<String, String> validateUpdatePaymentMethodApiRequest(BigDecimal id, UpdatePaymentMethodApiRequest request) {
        Map<String, String> errors = new HashMap<>();
        if (isNull(id)) errors.put("id", Errors.EMPTY_ID);
        if (!StringUtils.isEmpty(request.getName()) && (request.getName().length() > Constants.MAX_LENGTH || request.getName().length() < Constants.MIN_LENGTH)) errors.put("name", Errors.INVALID_NAME_LENGTH);
        if (!StringUtils.isEmpty(request.getDisplayName()) && (request.getDisplayName().length() > Constants.MAX_LENGTH || request.getDisplayName().length() < Constants.MIN_LENGTH)) errors.put("displayName", Errors.INVALID_DISPLAY_NAME_LENGTH);
        if (!StringUtils.isEmpty(request.getPaymentType()) && (request.getPaymentType().length() > Constants.MAX_LENGTH || request.getPaymentType().length() < Constants.MIN_LENGTH)) errors.put("paymentType", Errors.INVALID_PAYMENT_TYPE_LENGTH);
        for (PaymentPlanDto paymentPlanDto : request.getPaymentPlans()) {
            validateUpdatePaymentMethodApiPaymentPlan(paymentPlanDto, errors);
        }
        return errors;
    }

    private static void validateUpdatePaymentMethodApiPaymentPlan(PaymentPlanDto paymentPlan, Map<String, String> errors) {
        if (nonNull(paymentPlan.getDuration()) && (paymentPlan.getDuration().length() > Constants.MAX_LENGTH || paymentPlan.getDuration().length() < Constants.MIN_LENGTH)) errors.put("paymentPlan.duration", Errors.INVALID_DURATION_FIELD_LENGTH);
        if (nonNull(paymentPlan.getCurrency()) && (paymentPlan.getCurrency().length() > Constants.MAX_LENGTH || paymentPlan.getCurrency().length() < Constants.MIN_LENGTH)) errors.put("paymentPlan.currency", Errors.INVALID_CURRENCY_FIELD_LENGTH);
    }
}
