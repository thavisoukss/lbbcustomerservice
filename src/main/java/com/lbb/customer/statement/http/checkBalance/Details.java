package com.lbb.customer.statement.http.checkBalance;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Details {
    private String acctNo;
    private String certificateNo;
    private String acctDesc;
    private String clientNo;
    private String clientShort;
    private String globalIdType;
    private String globalId;
    private String branch;
    private String acctType;
    private String contractType;
    private String depositType;
    private String ccy;
    private String acctStatus;



    private String acctStatusDesc;
    private BigDecimal ledgerBal;
    private BigDecimal actualBal;
    private BigDecimal availBal;
    private BigDecimal principalAmt;
    private String reclassType;
    private String doubtfulStatus;
    private int noExpiredDays;
    private int fundsHeld;


    private int odLimit;
    private int toleranceAmt;
    private int indCpgAvailBal;
    private int totalFutureTranCr;
    private int totalFutureTranDr;
    private BigDecimal avgDailyBal;
    private int floats;
    private int uncollFees;
    private int batchFees;

    private int version;
    private int bizVersion;
    private String createdBy;
    private String createdDt;
    private String journalDt;
    private String journalNo;
    private String modifiedBy;
    private String modifiedDt;
    private String securityHint;


    private String digitalSignature;
    private String hasNextYn;
    private String hasError;
    private String publicKey;
    private String lastSuccessfulValidation;
    private String changeSummary;

    List<VirtualAttributeList> virtualAttributeList;
    List<DataExtensionList> dataExtensionList;
    Header header;
    List<MessageInfoList> messageInfoList;


}
