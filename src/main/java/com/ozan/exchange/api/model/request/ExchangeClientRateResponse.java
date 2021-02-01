package com.ozan.exchange.api.model.request;

import java.time.LocalDate;
import java.util.Map;
import lombok.Data;

@Data
public class ExchangeClientRateResponse {
    public String base;
    public Map<String, Double> rates;
    public LocalDate date;
}
