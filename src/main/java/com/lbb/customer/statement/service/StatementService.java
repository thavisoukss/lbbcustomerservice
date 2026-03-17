package com.lbb.customer.statement.service;

import com.lbb.customer.statement.db.service.ListAccountService;
import com.lbb.customer.statement.http.StatementClient;
import com.lbb.customer.statement.http.termdeposit.TermDepositInqueryReq;
import com.lbb.customer.statement.http.termdeposit.TermDepositInqueryRes;
import com.lbb.customer.statement.model.StatementReq;
import com.lbb.customer.statement.model.StatementRes;
import com.lbb.customer.statement.http.checkBalance.GetBalanceReq;
import com.lbb.customer.statement.http.checkBalance.GetBalanceRes;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService {
    private static final Logger logger = LogManager.getLogger(StatementService.class);

    @Autowired
    StatementClient statementClient;

    @Autowired
    ListAccountService listAccountService;

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

    public ListAccountRes getTDAccount(DecodeTokenObject token , String phone ){

        ListAccountRes  result = new ListAccountRes();
        List<ListAccountData> data = new ArrayList<>();
        data = ConvertListAccTD(token, phone);
        result.setStatus("success");
        result.setData(data);
        return result;
    }

    public List<ListAccountData> ConvertListAccTD (DecodeTokenObject token , String phone){

        List<ListAccountData> data = new ArrayList<>();
        List<ListAccountData> ListData = listAccountService.getListTDAccount(token.getUserId());

        if(ListData != null && !ListData.isEmpty()){
            for (int i = 0; i < ListData.size(); i++) {

                ListAccountData accTD = new ListAccountData();
                AccountTDDetail  accDetail = getAccTDDetail(ListData.get(i).getAccount_number());
            accTD.setStatus(ListData.get(i).getStatus());
            accTD.setProfit(ListData.get(i).getProfit());
            accTD.setInterest(accDetail.getInterest());
            accTD.setBalance(ListData.get(i).getBalance());
            accTD.setCurrency(ListData.get(i).getCurrency());
            accTD.setAccount_type(ListData.get(i).getAccount_type());
            accTD.setAccount_name(ListData.get(i).getAccount_name());
            accTD.setDep_term_period(accDetail.getDep_term_period());
            accTD.setCreated_at(ListData.get(i).getCreated_at());
            accTD.setStart_date(accDetail.getStart_date());
            accTD.setEnd_date(accDetail.getEnd_date());
            accTD.setAccount_number(ListData.get(i).getAccount_number());
            accTD.setAccount_type("TD");
                data.add(accTD);
            }
        }
        return data;
    }

    public AccountTDDetail getAccTDDetail (String acc){

        TermDepositInqueryRes data = new TermDepositInqueryRes();
        AccountTDDetail result = new AccountTDDetail();
        TermDepositInqueryReq req = new TermDepositInqueryReq();
        req.setAccount(acc);

        data = getAccountDetail(req);
        result.setInterest(data.getDetails().getIntDetailRec().getCrAcctLevelIntRate());
        result.setStart_date(data.getDetails().getOrigAcctOpenDate());
        result.setEnd_date(data.getDetails().getIntDetailRec().getCrNextCycleDate());
        result.setDep_term_period(data.getDetails().getTdaRec().getDepTermPeriod());
        return  result;
    }









}
