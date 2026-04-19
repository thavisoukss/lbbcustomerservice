package com.lbb.customer.util;

import org.springframework.stereotype.Service;

@Service
public class ConvertTxnType {

    public String  checkTxnType(String txnType){

        if (txnType == null) return "UNKNOWN";

        switch (txnType) {
            case "TRD2":
                return "TRANSFER_CREDIT";
            case "TRD3":
                return "BUY_CREDIT_INTERNAL";
            case "TRWT":
                return "DEPOSIT";
            case "CASD":
                return "OTC";
            case "TRW3":
                return "BUY_DEBIT_INTERNAL";
            default:
                return txnType;
        }

    }
}
