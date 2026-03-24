package com.lbb.customer.statement.service;

import com.lbb.customer.statement.db.service.ListAccountService;
import com.lbb.customer.statement.http.StatementClient;
import com.lbb.customer.statement.http.checkBalance.GetBalanceReq;
import com.lbb.customer.statement.http.checkBalance.GetBalanceRes;
import com.lbb.customer.statement.http.termdeposit.TermDepositInqueryReq;
import com.lbb.customer.statement.http.termdeposit.TermDepositInqueryRes;
import com.lbb.customer.statement.model.CalulatorObject;
import com.lbb.customer.statement.model.StatementReq;
import com.lbb.customer.statement.model.StatementRes;
import com.lbb.customer.statement.model.listaccount.AccountTDDetail;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import com.lbb.customer.statement.model.listaccount.ListAccountRes;
import com.lbb.customer.util.DecodeTokenObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentStatementService {
    private static final Logger logger = LogManager.getLogger(CurrentStatementService.class);

    @Autowired
    StatementClient statementClient;

    @Autowired
    ListAccountService listAccountService;

    @Autowired
       TDInterestCalculator calculator;
    @Value("${external.api.core-banking.url}")
    private String baseUrl;



    public StatementRes getStatement (StatementReq req){

        StatementRes result = new StatementRes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = baseUrl+"/getStatement";

        return statementClient.getStatement(url, req ,headers , StatementRes.class );

    }

    public GetBalanceRes getBalance (GetBalanceReq req){

        GetBalanceRes result = new GetBalanceRes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = baseUrl+"/checkBalance";

        return statementClient.getBalance(url, req ,headers , GetBalanceRes.class );

    }

    public TermDepositInqueryRes getAccountDetail (TermDepositInqueryReq req){
        TermDepositInqueryRes result = new TermDepositInqueryRes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = baseUrl+"/termAccountInquery";

        return statementClient.getAccountDetail(url, req ,headers , TermDepositInqueryRes.class );

    }


}
