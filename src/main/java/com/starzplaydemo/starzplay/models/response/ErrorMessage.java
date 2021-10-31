package com.starzplaydemo.starzplay.models.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ErrorMessage extends ApiResponseBody {
    private String errors;
}
