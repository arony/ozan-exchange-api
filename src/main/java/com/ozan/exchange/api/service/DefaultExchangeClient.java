package com.ozan.exchange.api.service;

import com.ozan.exchange.api.model.reponse.RateResponse;
import com.ozan.exchange.api.model.request.ExchangeClientRateResponse;
import com.ozan.exchange.api.model.request.RateRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Service
public class DefaultExchangeClient implements ExchangeClient {

    private final RestTemplate restTemplate;

    @Override
    public RateResponse getRate(RateRequest rateRequest) {
        Map<String, String> requestParam = new HashMap<>(){{
            put("base", rateRequest.base);
            put("symbol", rateRequest.symbol);
        }};
        ExchangeClientRateResponse exchangeClientRateResponse = restTemplate
                .getForObject("/latest?base={base}&symbols={symbol}", ExchangeClientRateResponse.class, requestParam);
        return new RateResponse(exchangeClientRateResponse.rates.get(rateRequest.symbol));
    }

    @Override
    public void getSupportedSymbols() {

    }
}
