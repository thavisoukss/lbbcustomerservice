package com.lbb.customer.statement.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TranHistFcyRec {
    private Long seqNo;

    private String tranDate;

    private String acctNo;
    private String rateOrigin;
    private String rateReference;

    private String fromCcy;
    private String toCcy;

    private BigDecimal crossRate;
    private BigDecimal fromAmount;
    private BigDecimal toAmount;

    private String crossId;
    private String fromRateFlag;
    private String toRateFlag;

    private BigDecimal ovCrossRate;
    private BigDecimal fromXrate;
    private BigDecimal toXrate;
    private BigDecimal ovToAmount;

    private String fromId;
    private String toId;

    private BigDecimal baseEquivAmt;
    private String rateFlag;

    private String sourceModule;
    private BigDecimal equivWdlPenAmt;

    private String profitCentre;

    private Integer version;
    private Integer bizVersion;

    private String createdBy;

    private String createdDt;

    private String journalDt;

    private Long journalNo;

    private String modifiedBy;

    private String modifiedDt;

    private Object virtualAttributeList;
    private Object dataExtensionList;
    private Object header;
    private Object securityHint;
    private Object digitalSignature;
    private Object hasNextYn;
    private Object messageInfoList;
    private Object hasError;
    private Object publicKey;
    private Object lastSuccessfulValidation;
    private Object changeSummary;
}
