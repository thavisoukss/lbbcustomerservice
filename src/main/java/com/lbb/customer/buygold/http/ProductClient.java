package com.lbb.customer.buygold.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private static final Logger logger = LogManager.getLogger(ProductClient.class);
    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T, R> R getRate(String url,  T requestBody ,HttpHeaders headers , Class<R> responseType ) {
        logger.info("Calling  Gateway CoreBanking  API: URL={}", url);

        HttpEntity<T> entity = new HttpEntity<>(requestBody ,  headers);

        try {
            logger.info("start call api Gateway CoreBanking  Get Exchange rate   request : {}" ,requestBody);

            R response = restTemplate.postForObject(url, entity, responseType);

            logger.info("end call  api Gateway CoreBanking   Get Exchange rate    response : {}", response);
            return response;

        } catch (org.springframework.web.client.ResourceAccessException e) {

            logger.error("API Timeout or Connection Error: URL={}, Error={}", url, e.getMessage());
            throw new RuntimeException("Api Gateway CoreBanking  Get Exchange rate   unavailable (Timeout)");

        } catch (org.springframework.web.client.HttpStatusCodeException e) {
            // ກໍລະນີ API ຕອບກັບມາເປັນ Error Status (4xx, 5xx)
            logger.error("Api Gateway CoreBanking   Get Exchange rate Error Status: {}, Response: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;

        } catch (Exception e) {
            logger.error("Api Gateway CoreBanking   Get Exchange rate Error: {}", e.getMessage());
            throw e;
        }
    }
}
