package com.lbb.customer.buygold.http.exchangerate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRes {
    private String code;
    private int status;
    private String message;
    private long journalNo;
    ExchangeRateData data;
}
