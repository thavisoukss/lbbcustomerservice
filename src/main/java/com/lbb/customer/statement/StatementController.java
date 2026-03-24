package com.lbb.customer.statement;

import com.lbb.customer.statement.model.StatementReq;
import com.lbb.customer.statement.model.StatementRes;
import com.lbb.customer.statement.model.listaccount.ListAccountReq;
import com.lbb.customer.statement.model.listaccount.ListAccountRes;
import com.lbb.customer.statement.service.StatementService;

import com.lbb.customer.util.DecodeToken;
import com.lbb.customer.util.DecodeTokenObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


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
            @RequestHeader("accNo") String accountNo,
            @RequestHeader("limit") String limit,
            @RequestHeader("offset") String offset
    ) {
        StatementReq req = new StatementReq();
        req.setAcctNo(accountNo);
        req.setLimit(limit);
        req.setOffset(offset);


        decodeToken.decodeToken(authentication);


        return statementService.getStatement(req);
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



}
