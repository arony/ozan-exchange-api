package com.ozan.exchange.api.config;

import java.io.IOException;
import java.net.URI;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@AllArgsConstructor
public class FixerAuthInterceptor implements ClientHttpRequestInterceptor {

    private final String accessKey;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        return execution.execute(new CustomHttpRequestWrapper(request), body);
    }

    private class CustomHttpRequestWrapper extends HttpRequestWrapper {
        public CustomHttpRequestWrapper(HttpRequest request) {
            super(request);
        }

        @Override
        public URI getURI() {
            return UriComponentsBuilder
                    .fromUri(getRequest().getURI())
                    .queryParam("access_key", accessKey)
                    .build().toUri();
        }
    }
}
