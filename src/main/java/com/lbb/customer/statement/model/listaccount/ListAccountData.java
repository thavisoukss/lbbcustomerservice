package com.lbb.customer.statement.model.listaccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ListAccountData {
    private String transaction_id;
    private String account_number;
    private String account_name;
    private String account_type;
    private BigDecimal balance;
    private String currency;
    private String start_date;
    private String end_date;
    private int dep_term_period;
    private BigDecimal interest;
    private BigDecimal profit;
    private String created_at;
    private String status;


    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializing to JSON: " + e.getMessage();
        }
    }
}
