package com.starzplaydemo.starzplay.models.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse extends BaseResponse {
    private int status;
    private String message;
    private ApiResponseBody body;
}
