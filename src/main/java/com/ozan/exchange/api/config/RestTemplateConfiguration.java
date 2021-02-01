package com.ozan.exchange.api.config;

import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Configuration
public class RestTemplateConfiguration {

    private final ExchangeApiConfiguration exchangeApiConfiguration;

    @Bean
    @ConditionalOnProperty(value = "exchange.api.provider", havingValue = "FIXER", matchIfMissing = true)
    public RestTemplate fixerRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri(exchangeApiConfiguration.api.url)
                .interceptors(Collections.singletonList(new FixerAuthInterceptor(exchangeApiConfiguration.api.accessKey))).build();
    }

    @Bean
    @ConditionalOnProperty(value = "exchange.api.provider", havingValue = "RATES", matchIfMissing = true)
    public RestTemplate ratesRestTemplate() {
        return new RestTemplateBuilder()
                .rootUri(exchangeApiConfiguration.api.url)
                .build();
    }
}
