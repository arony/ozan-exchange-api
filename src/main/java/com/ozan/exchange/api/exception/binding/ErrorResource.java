package com.ozan.exchange.api.exception.binding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResource {

    private int status;
    private String code;
    private String message;
    private List<FieldErrorResource> fieldErrors;

    public ErrorResource(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
