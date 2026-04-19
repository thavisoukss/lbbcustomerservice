package com.lbb.customer.buygold.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fee {
    private String currency;
    private BigDecimal fee;
}
