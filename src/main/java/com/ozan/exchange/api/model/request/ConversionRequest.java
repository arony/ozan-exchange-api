package com.ozan.exchange.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ConversionRequest {
    public String source;
    public Double amount;
    public String target;
}
