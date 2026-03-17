package com.lbb.customer.statement.http.checkBalance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBalanceReq {
    private String account;
}
