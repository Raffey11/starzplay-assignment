package com.starzplaydemo.starzplay.models.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse extends BaseResponse {
    private int status;
    private String message;
    private ErrorResponseBody body;
}
