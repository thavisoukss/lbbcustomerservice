package com.lbb.customer.statement.service;

import com.lbb.customer.statement.db.service.ListAccountService;
import com.lbb.customer.statement.http.StatementClient;
import com.lbb.customer.statement.http.listtxn.Credit;
import com.lbb.customer.statement.http.listtxn.Debit;
import com.lbb.customer.statement.http.listtxn.TxnData;
import com.lbb.customer.statement.http.listtxn.TransactionResponse;
import com.lbb.customer.statement.http.termdeposit.TermDepositInqueryReq;
import com.lbb.customer.statement.http.termdeposit.TermDepositInqueryRes;
import com.lbb.customer.statement.model.CalulatorObject;
import com.lbb.customer.statement.model.StatementReq;
import com.lbb.customer.statement.model.StatementRes;
import com.lbb.customer.statement.http.checkBalance.GetBalanceReq;
import com.lbb.customer.statement.http.checkBalance.GetBalanceRes;
import com.lbb.customer.statement.model.TransactionData;
import com.lbb.customer.statement.model.listaccount.AccountTDDetail;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import com.lbb.customer.statement.model.listaccount.ListAccountRes;
import com.lbb.customer.util.ConvertTxnType;
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
public class StatementService {
    private static final Logger logger = LogManager.getLogger(StatementService.class);

    @Autowired
    StatementClient statementClient;

    @Autowired
    ListAccountService listAccountService;

    @Autowired
       TDInterestCalculator calculator;
    @Autowired
    ConvertTxnType convertTxnType;

    @Value("${external.api.core-banking.url}")
    private String baseUrl;



    public StatementRes getStatement (StatementReq req){

        StatementRes result = new StatementRes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = baseUrl+"/getStatement";

        return statementClient.getStatement(url, req ,headers , StatementRes.class );
    }


    public TransactionResponse  getStatementV1 ( String user ,String method , String size){

        logger.info("******** get current account by user id *********** ");

        List<ListAccountData> ListData = listAccountService.getListCurrentAccount(user);
        ListAccountData account = new ListAccountData();
        logger.info(ListData);
        logger.info("******* get account number by ccy =  " + method + " ***********");
        account = getAccountByCcy(ListData , method);
        logger.info(account);

        TransactionResponse aa = new TransactionResponse();
        aa = convertStatementCurrentAcc(account , size);

        return  convertStatementCurrentAcc(account , size);
    }

    public TransactionResponse convertStatementCurrentAcc ( ListAccountData account , String size ){
       logger.info("*********** get Statement By Account ********** ");
        TransactionResponse result = new TransactionResponse();
        List<TxnData> listData = new ArrayList<>();

        StatementRes listStatement = new StatementRes();
        StatementReq statementReq = new StatementReq();
        statementReq.setOffset("1");
        statementReq.setLimit(size);
        statementReq.setAcctNo(account.getAccount_number());
        listStatement = getStatement(statementReq);
        List<TransactionData> txnData = listStatement.getDetails().getData();

        logger.info("******** mapping to  response ");

        for(int i = 0 ; i < txnData.size() ; i++){
            TxnData data = new TxnData();
            data.setTransactionId("");
            data.setTranType(convertTxnType.checkTxnType(txnData.get(i).getTranType()));
            data.setTranDate(txnData.get(i).getTimeStamp());
            data.setBankDate(txnData.get(i).getEffectDate());
            data.setSide(txnData.get(i).getCrDrMaintInd());
            data.setAmount(String.valueOf(txnData.get(i).getTranAmt()));
            data.setCurrency(txnData.get(i).getCcy());
            data.setFee("");
            data.setSeqNo(txnData.get(i).getTfrSeqNo());
            data.setFromBank("");
            data.setBankRef("");
            data.setTranStatus("");
            data.setTranSource("");
            data.setDescription("");
            if(txnData.get(i).getCrDrMaintInd().equals("D")){
                Debit dr = new Debit();
                dr.setAccount_no(txnData.get(i).getAcctNo());
                dr.setPrevious_bal(txnData.get(i).getPreviousBalAmt());
                dr.setActual_bal(txnData.get(i).getActualBalAmt());
                dr.setProfit_centre(txnData.get(i).getProfitCentre());
                data.setDebit(dr);
            }else {
                Credit cr = new Credit();
                cr.setAccount_no(txnData.get(i).getAcctNo());
                cr.setPrevious_bal(txnData.get(i).getPreviousBalAmt());
                cr.setActual_bal(txnData.get(i).getActualBalAmt());
                cr.setTfr_account_no(txnData.get(i).getTfrAcctNo());
                cr.setProfit_centre(txnData.get(i).getProfitCentre());
                data.setCredit(cr);
            }
            listData.add(data);
        }
        result.setData(listData);
        result.setStatus("success");
        return result;
    }

    public ListAccountData getAccountByCcy (List<ListAccountData>  data , String ccy){
        ListAccountData result = new ListAccountData();

        int index = -1;

        for (int i = 0; i < data.size(); i++) {
            if (ccy.equals(data.get(i).getCurrency())) {
                index = i;
                result = data.get(i);
                break;
            }
        }
        return result;
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

    public ListAccountRes getCurrentAccount(DecodeTokenObject token , String phone ){

        ListAccountRes  result = new ListAccountRes();
        List<ListAccountData> data = new ArrayList<>();
        data = ConvertListAccCurrent(token, phone);
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
                CalulatorObject Objcalulator = new CalulatorObject();
                LocalDate startDate = OffsetDateTime.parse(accDetail.getStart_date()).toLocalDate();
                Objcalulator = calculator.calculateTDInterestService(accDetail.getAmount(),accDetail.getInterest(),accDetail.getDep_term_period(),startDate);

            accTD.setStatus(ListData.get(i).getStatus());
            accTD.setProfit(Objcalulator.getInterestAmount());
            accTD.setInterest(accDetail.getInterest());
            accTD.setBalance(accDetail.getAmount());
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

    public List<ListAccountData> ConvertListAccCurrent (DecodeTokenObject token , String phone){

        List<ListAccountData> data = new ArrayList<>();
        List<ListAccountData> ListData = listAccountService.getListCurrentAccount(token.getUserId());
        logger.info(ListData);

//        if(ListData != null && !ListData.isEmpty()){
//            for (int i = 0; i < ListData.size(); i++) {
//
//                ListAccountData accTD = new ListAccountData();
//                AccountTDDetail  accDetail = getAccTDDetail(ListData.get(i).getAccount_number());
//                CalulatorObject Objcalulator = new CalulatorObject();
//                LocalDate startDate = OffsetDateTime.parse(accDetail.getStart_date()).toLocalDate();
//                Objcalulator = calculator.calculateTDInterestService(accDetail.getAmount(),accDetail.getInterest(),accDetail.getDep_term_period(),startDate);
//
//                accTD.setStatus(ListData.get(i).getStatus());
//                accTD.setProfit(Objcalulator.getInterestAmount());
//                accTD.setInterest(accDetail.getInterest());
//                accTD.setBalance(accDetail.getAmount());
//                accTD.setCurrency(ListData.get(i).getCurrency());
//                accTD.setAccount_type(ListData.get(i).getAccount_type());
//                accTD.setAccount_name(ListData.get(i).getAccount_name());
//                accTD.setDep_term_period(accDetail.getDep_term_period());
//                accTD.setCreated_at(ListData.get(i).getCreated_at());
//                accTD.setStart_date(accDetail.getStart_date());
//                accTD.setEnd_date(accDetail.getEnd_date());
//                accTD.setAccount_number(ListData.get(i).getAccount_number());
//                accTD.setAccount_type("CurrentAccount");
//                data.add(accTD);
//            }
//        }
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
        result.setAmount(data.getDetails().getTdaRec().getPrincipalAmt());

        return  result;
    }









}
