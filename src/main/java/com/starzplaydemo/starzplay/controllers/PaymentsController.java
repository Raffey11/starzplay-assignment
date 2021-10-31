package com.starzplaydemo.starzplay.controllers;

import com.starzplaydemo.starzplay.models.request.CreatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.models.request.GetPaymentMethodsRequest;
import com.starzplaydemo.starzplay.models.request.UpdatePaymentMethodApiRequest;
import com.starzplaydemo.starzplay.models.response.BaseResponse;
import com.starzplaydemo.starzplay.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1.0/configuration")
public class PaymentsController {

    @Autowired
    private PaymentService paymentService;

    Logger logger = LoggerFactory.getLogger(PaymentsController.class);

    @GetMapping(value = "/payment-methods",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<BaseResponse> getPaymentMethods(@RequestParam(value = "name", required = false) String name,
                                                          @RequestParam(value = "id", required = false) BigDecimal id) {
        logger.info("Get Payment Method(s) API called");
        return ResponseEntity.ok(paymentService.getPaymentMethods(GetPaymentMethodsRequest.builder()
                .id(id)
                .name(name)
                .build()));
    }

    @PostMapping(value = "/payment-methods",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<BaseResponse> createPaymentMethod(@RequestBody CreatePaymentMethodApiRequest request) {
        logger.info("Create Payment Method API called");
        return ResponseEntity.ok(paymentService.createPaymentMethod(request));
    }

    @PutMapping(value = "/payment-methods",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<BaseResponse> updatePaymentMethod(@RequestParam(value = "id") BigDecimal id,
                                                            @RequestBody UpdatePaymentMethodApiRequest request) {
        logger.info("Update Payment Method API called");
        return ResponseEntity.ok(paymentService.updatePaymentMethod(id, request));
    }
}
