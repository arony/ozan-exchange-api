package com.ozan.exchange.api.service;


import com.ozan.exchange.api.model.reponse.RateResponse;
import com.ozan.exchange.api.model.request.RateRequest;

public interface ExchangeClient {
    RateResponse getRate(RateRequest rateRequest);

    void getSupportedSymbols();
}
