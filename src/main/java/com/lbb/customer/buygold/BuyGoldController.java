package com.lbb.customer.buygold;

import com.lbb.customer.statement.model.StatementReq;
import com.lbb.customer.statement.model.StatementRes;
import com.lbb.customer.statement.service.StatementService;

import com.lbb.customer.util.DecodeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("${baseurl}")
public class BuyGoldController {

    private static final Logger logger = LogManager.getLogger(BuyGoldController.class);

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

}
