package com.lbb.customer.statement.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StatementClient {
    private static final Logger logger = LogManager.getLogger(StatementClient.class);
    private final RestTemplate restTemplate;

    public StatementClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public <T, R> R getStatement(String url, T requestBody, HttpHeaders headers , Class<R> responseType ) {
        logger.info("Calling  Gateway CoreBanking  API: URL={}", url);

        HttpEntity<T> entity = new HttpEntity<>(requestBody, headers);

        try {
            logger.info("start call api Gateway CoreBanking  Statement  request : {}", requestBody);

            R response = restTemplate.postForObject(url, entity, responseType);

            logger.info("end call  api Gateway CoreBanking  Statement   response : {}", response);
            return response;

        } catch (org.springframework.web.client.ResourceAccessException e) {

            logger.error("API Timeout or Connection Error: URL={}, Error={}", url, e.getMessage());
            throw new RuntimeException("Api Gateway CoreBanking  Statement   unavailable (Timeout)");

        } catch (org.springframework.web.client.HttpStatusCodeException e) {
            // ກໍລະນີ API ຕອບກັບມາເປັນ Error Status (4xx, 5xx)
            logger.error("Api Gateway CoreBanking  Statement Error Status: {}, Response: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;

        } catch (Exception e) {
            logger.error("Api Gateway CoreBanking  Statement Error: {}", e.getMessage());
            throw e;
        }
    }

    public <T, R> R getBalance(String url, T requestBody, HttpHeaders headers , Class<R> responseType ) {
        logger.info("Calling  Gateway CoreBanking  GetBalance: URL={}", url);

        HttpEntity<T> entity = new HttpEntity<>(requestBody, headers);

        try {
            logger.info("start call api Gateway CoreBanking  GetBalance  request : {}", requestBody);

            R response = restTemplate.postForObject(url, entity, responseType);

            logger.info("end call  api Gateway CoreBanking  GetBalance   response : {}", response);
            return response;

        } catch (org.springframework.web.client.ResourceAccessException e) {

            logger.error("API Timeout or Connection Error: URL={}, Error={}", url, e.getMessage());
            throw new RuntimeException("Api Gateway CoreBanking  GetBalance   unavailable (Timeout)");

        } catch (org.springframework.web.client.HttpStatusCodeException e) {
            // ກໍລະນີ API ຕອບກັບມາເປັນ Error Status (4xx, 5xx)
            logger.error("Api Gateway CoreBanking  Statement Error GetBalance: {}, Response: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;

        } catch (Exception e) {
            logger.error("Api Gateway CoreBanking  GetBalance Error: {}", e.getMessage());
            throw e;
        }
    }

    public <T, R> R getAccountDetail(String url, T requestBody, HttpHeaders headers , Class<R> responseType ) {
        logger.info("Calling  Gateway CoreBanking  AccountDetail: URL={}", url);

        HttpEntity<T> entity = new HttpEntity<>(requestBody, headers);

        try {
            logger.info("start call api Gateway CoreBanking  AccountDetail  request : {}", requestBody);

            R response = restTemplate.postForObject(url, entity, responseType);

            logger.info("end call  api Gateway CoreBanking  AccountDetail   response : {}", response);
            return response;

        } catch (org.springframework.web.client.ResourceAccessException e) {

            logger.error("API Timeout or Connection Error: URL={}, Error={}", url, e.getMessage());
            throw new RuntimeException("Api Gateway CoreBanking  AccountDetail   unavailable (Timeout)");

        } catch (org.springframework.web.client.HttpStatusCodeException e) {
            // ກໍລະນີ API ຕອບກັບມາເປັນ Error Status (4xx, 5xx)
            logger.error("Api Gateway CoreBanking  Statement Error AccountDetail: {}, Response: {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw e;

        } catch (Exception e) {
            logger.error("Api Gateway CoreBanking  GetBalance Error: {}", e.getMessage());
            throw e;
        }
    }


}
