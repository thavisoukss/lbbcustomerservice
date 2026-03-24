package com.lbb.customer.statement.model.currentstatement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitInfo {
    private String profit_centre;
    private String account_no;
    private BigDecimal previous_bal;
    private BigDecimal actual_bal;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializing to JSON: " + e.getMessage();
        }
    }
}
