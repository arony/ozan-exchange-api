package com.ozan.exchange.api.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "exchange")
public class ExchangeApiConfiguration {

    @Valid
    public Api api = new Api();

    @Data
    public static class Api {

        public Provider provider = Provider.RATES;

        @NotNull(message = "Please set exchange API URL")
        public String url;

        @NotNull(message = "Please set exchange API access key")
        public String accessKey;

        public enum Provider {
            FIXER,
            RATES,
        }
    }
}
