package com.lbb.customer.statement.http.termdeposit;

import lombok.Data;

@Data
public class AcctStmtRec {
    private String acctNo;
    private String distrChannel;
    private String template;
    private Integer lastStmtNo;
    private String stmtType;
    private String consSc;
    private String stmtAfterMovement;
    private String stmtAtCap;
    private String suppressPrint;
    private String contactRefNo;
    private String clientName;
    private String address;
    private String postalCode;
    private String onMaturityStmt;
    private String stmtHandling;
    private String suspendMailCode;
    private String periodFreq;

    private String nextStmtDate;   // keep String (safe) or use LocalDateTime
    private String stmtDay;
    private String ctrlCommand;
    private String stmtLang;
    private String cmlStmtCode;

    private Integer stmtRefNo;
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
