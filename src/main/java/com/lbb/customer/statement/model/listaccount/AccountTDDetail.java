package com.lbb.customer.statement.model.listaccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountTDDetail {
    private String start_date;
    private String end_date;
    private Integer dep_term_period;
    private BigDecimal interest;
    private BigDecimal profit;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializing to JSON: " + e.getMessage();
        }
    }

}
