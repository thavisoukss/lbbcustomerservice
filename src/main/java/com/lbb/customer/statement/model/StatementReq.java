package com.lbb.customer.statement.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class StatementReq {

    private String limit;
    private String offset;
    private String acctNo;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializing to JSON: " + e.getMessage();
        }
    }

}
