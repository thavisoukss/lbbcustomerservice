package com.lbb.customer.buygold.http.exchangerate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateReq {
    private String branch;
    private String ccy;
    private boolean historyYn;
    private String xrateType;
}
