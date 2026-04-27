package com.lbb.customer.createaccount;

import com.lbb.customer.buygold.model.GetProductRes;
import com.lbb.customer.createaccount.model.*;
import com.lbb.customer.createaccount.service.CreateAccountService;
import com.lbb.customer.util.DecodeToken;
import com.lbb.customer.util.DecodeTokenObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${baseurl}")
public class CreateAccountController {
    private static final Logger logger = LogManager.getLogger(CreateAccountController.class);

    @Autowired
    CreateAccountService createAccountService;

    @Autowired
    DecodeToken decodeToken;


    @GetMapping(value = "/td/get-product",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public GetMonthRes getProduct(
            Authentication authentication
    ) {

        DecodeTokenObject decodeTokenObject = new DecodeTokenObject();
        decodeTokenObject = decodeToken.decodeToken(authentication);
        logger.info(decodeTokenObject);

        GetMonthRes result = new GetMonthRes();
        result = createAccountService.getProduct();
        return result;
    }


    @PostMapping(value = "/calculate-interest",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public CalulateInterateRes calulateInterate  ( @RequestBody CalulateInterateReq req ,  Authentication authentication ){
        DecodeTokenObject decodeTokenObject = new DecodeTokenObject();
        decodeTokenObject = decodeToken.decodeToken(authentication);
        return  createAccountService.calulateInterate(decodeTokenObject, req);
    }

    @PostMapping(value = "/inquery-open-account",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public InQueryAccountTDRes inquery (@RequestBody InQueryAccountTDReq req ,  Authentication authentication ){

        return  createAccountService.inqueryAccount(req);
    }

    @PostMapping(value = "/confirm-open-account",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public ConfirmCreateAccountRes confirm  (@RequestBody ConfirmCreateAccountReq req ,  Authentication authentication ){

        return  createAccountService.confirmCreateAccount(req);
    }





}
