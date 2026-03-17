package com.lbb.customer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class DecodeTokenObject {
    private String aud;
    private int exp;
    private int iat;
    private String isu;
    private String sid;
    private String sub;
    private String type;
    private String userId;
    private String userPhone;
    private String userRole;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializing to JSON: " + e.getMessage();
        }
    }


}
