package com.lbb.customer.statement.http.termdeposit;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntDetailRec {
    private String acctNo;
    private BigDecimal crAcctLevelIntRate;
    private BigDecimal crSpreadRate;
    private String crEffectiveRate;
    private String crIntType;
    private String crTaxType;
    private String crPeriodFreq;
    private String crNextCycleDate;
    private Integer crIntDay;
    private String crThirdPartyAcctNo;
    private String crCofAppType;
    private String crFundType;
    private BigDecimal crCofRate;
    private BigDecimal crCofLiqPremia;
    private BigDecimal crCofEffectRate;
    private BigDecimal drAcctLevelIntRate;
    private BigDecimal drSpreadRate;
    private BigDecimal drEffectiveRate;
    private String drIntType;
    private BigDecimal penaltyMarginRate;
    private String drPeriodFreq;
    private String drNextCycleDate;
    private Integer drIntDay;
    private String drThirdPartyAcctNo;
    private String drCofAppType;
    private String drFundType;
    private BigDecimal drCofRate;
    private BigDecimal drCofLiqPremia;
    private BigDecimal drCofEffectRate;
    private String tdRelationInd;
    private String crPrinAcct;
    private String crPrinAcctCcy;
    private String crIntRateInd;
    private String crTdPayout;
    private String capOnDec31;
    private String interestBonf;
    private String crIntAcct;
    private String crIntAcctCcy;
    private String crRealznIntType;
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
    private String hasNextYn;
    private Object messageInfoList;
    private Boolean hasError;
    private String publicKey;
    private String lastSuccessfulValidation;
    private String changeSummary;
}
