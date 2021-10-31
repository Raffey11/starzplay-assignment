package com.starzplaydemo.starzplay.models.response;

import lombok.*;

import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseBody {
    private Map<String, String> errors;
}
