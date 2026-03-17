package com.lbb.customer.statement.http.checkBalance;

import lombok.Data;

@Data
public class Header {
    private String serviceOperation;
    private String applUserCode;
    private String organisationCode;
    private String userRoleList;
    private String dataChannelCode;
    private String langCode;
    private String corrId;
    private String screenNo;
    private String bpmInfo;
    private String clientInfo;

    private String creationTime;
    private String typeName;
    private String channelSource;
    private String channelTranDt;
    private String resend;

    private String requestUri;
    private String hasAuthToken;

}
