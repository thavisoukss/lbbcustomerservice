package com.lbb.customer.createaccount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmCreateAccountRes {
    private String message;
    private String status;
    private ConfirmCreateAccountData data;

}
