package com.lbb.customer.statement.http.termdeposit;

import lombok.Data;

import java.util.List;

@Data
public class ProdScDefRec {
    private String prodNo;
    private String moduleId;
    private String subType;
    private String stopSc;
    private String thirdPartyAcctNo;
    private String scException;
    private String scGroupCode;
    private String scPackType;

    private String fwEligible;
    private String fwStatus;
    private String taxFiler ;
    private int version;
    private int bizVersion;
    private String createdBy;
    private String createdDt;
    private String journalDt ;
    private int journalNo;

    private String modifiedBy;
    private String modifiedDt;
    List<VirtualAttributeList> virtualAttributeList;
    List<DataExtensionList> dataExtensionList;
    private String header;
    private String securityHint;

    private String digitalSignature;
    private String hasNextYn;
    List<MessageInfoList> messageInfoList;
    private String hasError ;
    private String publicKey ;
    private String lastSuccessfulValidation ;
    private String changeSummary;

}
