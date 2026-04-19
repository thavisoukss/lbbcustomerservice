package com.lbb.customer.statement.http.listtxn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxnData {

    private String transactionId;
    private String tranType;

    private String tranDate;

    private String bankDate;

    private String side;
    private String amount;
    private String currency;
    private String fee;
    private Long seqNo;

    private String fromBank;
    private String bankRef;
    private String tranStatus;
    private String tranSource;
    private String description;

    private Debit debit;
    private Credit credit;


}
