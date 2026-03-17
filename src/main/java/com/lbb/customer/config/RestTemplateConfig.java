package com.lbb.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Value("${external.api.client.connect-timeout}")
    private int connectTimeout;

    @Value("${external.api.client.read-timeout}")
    private int readTimeout;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        RestTemplate restTemplate = new RestTemplate(factory);

        // ເພີ່ມ Interceptor ເຂົ້າໄປ
       // restTemplate.getInterceptors().add(new BearerTokenInterceptor(token));

        return restTemplate;
    }
}
