package com.lbb.customer.createaccount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InQueryAccountTDData {
private String reference;
private String from_account_name;
private String to_account_name;
private String branch_name;
private  int period;
private  BigDecimal rate;
private double per_gram;
private String td_code;
private String currency;
private BigDecimal interest_amount;


}
