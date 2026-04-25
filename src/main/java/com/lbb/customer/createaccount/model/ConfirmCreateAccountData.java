package com.lbb.customer.createaccount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmCreateAccountData {
    private String transactionId;
    private String accountNo;
    private String tdCode;
    private String accName;
    private BigDecimal principalAmt;
    private String ccy;
    private String acctOpenDate;
    private String maturityDate;
    private BigDecimal interestRate;
    private int period;
    private String periodUnit;
    private String createdDt;
    private String autoRenewRollover;
    private String slipCode;

}
