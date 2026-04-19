package com.lbb.customer.statement.http.listtxn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponse {
    private List<TxnData> data;
    private Meta meta;
    private String status;
}
