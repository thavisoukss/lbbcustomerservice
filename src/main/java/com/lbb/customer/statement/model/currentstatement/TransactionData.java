package com.lbb.customer.statement.model.currentstatement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("tran_type")
    private String tranType;

    @JsonProperty("tran_date")
    private ZonedDateTime tranDate;

    @JsonProperty("bank_date")
    private ZonedDateTime bankDate;

    @JsonProperty("side")
    private String side;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("fee")
    private String fee;

    @JsonProperty("seq_no")
    private Integer seqNo;

    @JsonProperty("from_bank")
    private String fromBank;

    @JsonProperty("bank_ref")
    private String bankRef;

    @JsonProperty("tran_status")
    private String tranStatus;

    @JsonProperty("tran_source")
    private String tranSource;

    @JsonProperty("description")
    private String description;

    @JsonProperty("debit")
    private DebitInfo debit;

    @JsonProperty("credit")
    private CreditInfo credit;

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "Error serializing to JSON: " + e.getMessage();
        }
    }
}
