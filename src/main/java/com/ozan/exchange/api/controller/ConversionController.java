package com.ozan.exchange.api.controller;

import com.ozan.exchange.api.model.entity.Conversion;
import com.ozan.exchange.api.model.request.ConversionRequest;
import com.ozan.exchange.api.service.ExchangeConversionService;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/conversion")
public class ConversionController {

    private final ExchangeConversionService exchangeConversionService;

    @GetMapping
    public List<Conversion> getAll(){
        return exchangeConversionService.findAll();
    }

    @GetMapping("/{transactionId}")
    public Conversion getOneByTransaction(@PathVariable UUID transactionId){
        return exchangeConversionService.findByTransactionId(transactionId);
    }

    @GetMapping("/date/{date}")
    public List<Conversion> getOneByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return exchangeConversionService.findByDate(date);
    }

    @GetMapping("/calculate")
    public Conversion calculate(ConversionRequest conversionRequest){
        return exchangeConversionService.calculate(conversionRequest);
    }
}
