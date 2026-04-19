package com.lbb.customer.buygold.http.exchangerate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateData {
    private double midRate;
    private double buyRate;
    private double sellRate;
}
