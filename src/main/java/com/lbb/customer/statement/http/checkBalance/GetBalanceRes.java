package com.lbb.customer.statement.http.checkBalance;

import lombok.Data;

import java.util.List;

@Data
public class GetBalanceRes {
    private String code;
    public String status;
    public String responseType;
    public String message;
    public int journalNo;
    Details details;
    List<WarningInfoList> warningInfoList;
}
