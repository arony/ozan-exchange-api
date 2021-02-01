package com.ozan.exchange.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RateRequest {
    public String base;
    public String symbol;
}
