package com.ozan.exchange.api.controller;

import com.ozan.exchange.api.model.reponse.RateResponse;
import com.ozan.exchange.api.model.request.RateRequest;
import com.ozan.exchange.api.service.ExchangeClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/rates")
public class RateController {

    private final ExchangeClient exchangeClient;

    @GetMapping
    public RateResponse getRates(RateRequest rateRequest) {
        return exchangeClient.getRate(rateRequest);
    }
}
