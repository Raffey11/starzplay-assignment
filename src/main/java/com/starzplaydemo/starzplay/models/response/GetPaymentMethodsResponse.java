package com.starzplaydemo.starzplay.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.starzplaydemo.starzplay.dtos.PaymentMethodDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPaymentMethodsResponse extends ApiResponseBody {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<PaymentMethodDto> paymentMethods;
}
