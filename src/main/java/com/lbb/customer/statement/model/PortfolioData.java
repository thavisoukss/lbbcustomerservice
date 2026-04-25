package com.lbb.customer.statement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioData {
    private BigDecimal initial_total_amount;
    private BigDecimal current_total_amount;
    private String currency;
}
