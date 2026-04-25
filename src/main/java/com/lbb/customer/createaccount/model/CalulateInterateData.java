package com.lbb.customer.createaccount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalulateInterateData {
    private String reference;
    private String td_code;
    private BigDecimal principal_amount;
    private int period;
    private double rate;
    private  double per_gram;
    private String currency;
    private String start_date;
    private String end_date;
    private BigDecimal interest_amount;
    private String disclaimer;

}
