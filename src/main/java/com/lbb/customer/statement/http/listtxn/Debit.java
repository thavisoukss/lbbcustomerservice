package com.lbb.customer.statement.http.listtxn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Debit {
    private String profit_centre;
    private String account_no;
    private BigDecimal previous_bal;
    private BigDecimal actual_bal;
}
