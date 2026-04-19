package com.lbb.customer.buygold;

import com.lbb.customer.buygold.model.FeeReq;
import com.lbb.customer.buygold.model.FeeRes;
import com.lbb.customer.buygold.model.GetProductRes;
import com.lbb.customer.buygold.service.ListProductService;
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
    ListProductService listProductService;

    @Autowired
    DecodeToken decodeToken;


    @GetMapping(value = "/get-product",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public GetProductRes getProduct(
            Authentication authentication
    ) {
        return listProductService.getAllProduct();
    }

    @PostMapping(value = "/calculate-fee",
            produces = "application/json", // This tells the client we return JSON
            headers = {
                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
            })
    public FeeRes  getFee (@RequestBody FeeReq req , Authentication authentication ){

     return  listProductService.getFee(req);
    }

}
