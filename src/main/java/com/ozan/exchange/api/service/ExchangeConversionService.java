package com.ozan.exchange.api.service;

import com.ozan.exchange.api.exception.EntityNotFoundException;
import com.ozan.exchange.api.model.entity.Conversion;
import com.ozan.exchange.api.model.reponse.RateResponse;
import com.ozan.exchange.api.model.request.ConversionRequest;
import com.ozan.exchange.api.model.request.RateRequest;
import com.ozan.exchange.api.repository.ConversionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExchangeConversionService {

    private final ConversionRepository conversionRepository;
    private final ExchangeClient exchangeClient;

    public Conversion calculate(ConversionRequest conversionRequest) {
        RateResponse rateResponse = exchangeClient.getRate(new RateRequest(conversionRequest.source, conversionRequest.target));
        Double result = rateResponse.rate * conversionRequest.amount;
        Conversion conversion = new Conversion(null, conversionRequest.source, conversionRequest.target, conversionRequest.amount, result, LocalDate.now());
        return save(conversion);
    }

    public Conversion save(Conversion conversion) {
        return conversionRepository.save(conversion);
    }

    public List<Conversion> findAll() {
        return conversionRepository.findAll();
    }

    public Conversion findByTransactionId(UUID transactionId) {
        return conversionRepository.findById(transactionId).orElseThrow(() -> new EntityNotFoundException("Conversion not found"));
    }

    public List<Conversion> findByDate(LocalDate date) {
        return conversionRepository.findByDate(date);
    }

}
