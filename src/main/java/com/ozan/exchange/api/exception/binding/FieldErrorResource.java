package com.ozan.exchange.api.exception.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorResource {

    private String resource;
    private String field;
    private String code;
    private String message;
}
