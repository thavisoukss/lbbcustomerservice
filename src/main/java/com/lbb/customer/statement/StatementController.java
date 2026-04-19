package com.lbb.customer.statement;

import com.lbb.customer.statement.http.listtxn.TransactionResponse;
import com.lbb.customer.statement.model.StatementReq;
import com.lbb.customer.statement.model.StatementRes;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import com.lbb.customer.statement.model.listaccount.ListAccountReq;
import com.lbb.customer.statement.model.listaccount.ListAccountRes;
import com.lbb.customer.statement.service.StatementService;

import com.lbb.customer.util.DecodeToken;
import com.lbb.customer.util.DecodeTokenObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;


@RestController
@RequestMapping("${baseurl}")
public class StatementController {

    private static final Logger logger = LogManager.getLogger(StatementController.class);

    @Autowired
    StatementService statementService;

    @Autowired
    DecodeToken decodeToken;
    @GetMapping(value = "/getStatement",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public StatementRes getStatement(
            Authentication authentication,
            @RequestParam("accNo") String accountNo,
            @RequestParam("size") String limit,
            @RequestParam("method") String offset
    ) {
        StatementReq req = new StatementReq();
        req.setAcctNo(accountNo);
        req.setLimit(limit);
        req.setOffset(offset);

        decodeToken.decodeToken(authentication);
        return statementService.getStatement(req);
    }


    @GetMapping(value = "/get-statments",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public TransactionResponse statement(
            Authentication authentication,
            @RequestParam("method") String method,
            @RequestParam("size") String size
    ) {
        DecodeTokenObject decodeTokenObject = new DecodeTokenObject();
        decodeTokenObject =  decodeToken.decodeToken(authentication);
        return statementService.getStatementV1(decodeTokenObject.getUserId() , method , size);
    }


    @GetMapping(value = "/list-td-account",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })

    public ListAccountRes listTDAccount(
            Authentication authentication,
            @RequestBody ListAccountReq req
    ) {
        DecodeTokenObject decodeTokenObject = new DecodeTokenObject();
        decodeTokenObject =  decodeToken.decodeToken(authentication);

        return statementService.getTDAccount(decodeTokenObject ,req.getPhone() );
    }


    @GetMapping(value = "/list-current-account",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })

    public ListAccountRes listCurrentAccount(
            Authentication authentication,
            @RequestBody ListAccountReq req
    ) {
        DecodeTokenObject decodeTokenObject = new DecodeTokenObject();
        decodeTokenObject =  decodeToken.decodeToken(authentication);

        return statementService.getCurrentAccount(decodeTokenObject ,req.getPhone() );
    }

}
