package com.lbb.customer.buygold;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lbb.customer.buygold.model.FeeReq;
import com.lbb.customer.buygold.model.FeeRes;
import com.lbb.customer.buygold.model.GetProductRes;
import com.lbb.customer.buygold.service.ListProductService;

import com.lbb.customer.util.DecodeToken;
import com.lbb.customer.util.DecodeTokenObject;
import com.lbb.customer.util.DecodeTokenV2;
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

    @Autowired
    DecodeTokenV2 decodeTokenV2;


    @GetMapping(value = "/get-product",
            produces = "application/json"
//            , // This tells the client we return JSON
//            headers = {
//                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
//            }
            )
    public GetProductRes getProduct(
            Authentication authentication,
            @RequestHeader(value = "Authorization", required = false) String header
    ) {
        try {
            DecodeTokenObject tokenObject = decodeTokenV2.peekFromHeader(header);
            logger.info(tokenObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return listProductService.getAllProduct();
    }

    @PostMapping(value = "/calculate-fee",
            produces = "application/json"
//            , // This tells the client we return JSON
//            headers = {
//                    "Accept-Encoding=identity" // Keep this if your logic strictly requires it
//            }
            )
    public FeeRes  getFee (@RequestBody FeeReq req , Authentication authentication ,
                           @RequestHeader(value = "Authorization", required = false) String header){

     return  listProductService.getFee(req);
    }



}
