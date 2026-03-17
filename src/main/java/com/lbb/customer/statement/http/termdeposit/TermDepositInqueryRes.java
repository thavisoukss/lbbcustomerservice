package com.lbb.customer.statement.http.termdeposit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class TermDepositInqueryRes {
    private String code;
    private String status;
    private String responseType;
    private String message;
    private String journalNo;
   Details details;
   List<WarningInfoList> warningInfoList;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializing to JSON: " + e.getMessage();
        }
    }

}
