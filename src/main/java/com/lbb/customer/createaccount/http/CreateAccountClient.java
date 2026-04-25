package com.lbb.customer.createaccount.http;

import com.lbb.customer.buygold.service.ListProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CreateAccountClient {
    private static final Logger logger = LogManager.getLogger(CreateAccountClient.class);
    private final RestTemplate restTemplate;

    public CreateAccountClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
