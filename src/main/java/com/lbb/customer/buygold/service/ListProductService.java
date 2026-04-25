package com.lbb.customer.buygold.service;

import com.lbb.customer.buygold.db.service.ProductService;
import com.lbb.customer.buygold.http.ProductClient;
import com.lbb.customer.buygold.http.exchangerate.ExchangeRateReq;
import com.lbb.customer.buygold.http.exchangerate.ExchangeRateRes;
import com.lbb.customer.buygold.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListProductService {

    private static final Logger logger = LogManager.getLogger(ListProductService.class);

    @Value("${external.api.core-banking.url}")
    private String baseUrl;

    @Autowired
    ProductService productService;

    @Autowired
    ProductClient productClient;

    public GetProductRes getAllProduct (){
      List<ProductData> listProductData = new ArrayList<>();
        List<Product> data = new ArrayList<>();
        GetProductRes result = new GetProductRes();

        listProductData = productService.getAllProduct();

        logger.info("****** convert Rate *******");
        ExchangeRateRes exchangeRate = new ExchangeRateRes();
        ExchangeRateReq rateReq = new ExchangeRateReq();
        rateReq.setBranch("100");
        rateReq.setCcy("LBI");
        rateReq.setHistoryYn(false);
        rateReq.setXrateType("CSG");
        exchangeRate = getRate(rateReq);
        data = convertRate(listProductData , exchangeRate);
        result.setData(data);
        result.setSuccess("success");
        return result;
    }

    public ExchangeRateRes getRate (ExchangeRateReq req){

        ExchangeRateRes result = new ExchangeRateRes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = baseUrl+"/getRate";
        result = productClient.getRate(url,req,headers,ExchangeRateRes.class);

     return result;
    }

    public List<Product> convertRate (List<ProductData>  data , ExchangeRateRes buyRate){

        List<Product> result = new ArrayList<>();
        if(data.size() > 0 ){
            for(int i = 0 ; i < data.size() ; i++){
                Product product = new Product();
                product.setProduct_id(String.valueOf(data.get(i).getId()));
                product.setProduct_name(data.get(i).getProductName());
                product.setQuantity(BigDecimal.valueOf(data.get(i).getQuantity()));
                product.setUnit(data.get(i).getUnit());
                product.setImage_url(data.get(i).getImage());
                product.setAmount(BigDecimal.valueOf(data.get(i).getQuantity() * buyRate.getData().getBuyRate()));
                result.add(product);
            }
            return result;
        }
           return result;
    }

    public FeeRes getFee (FeeReq req){
        FeeRes result = new FeeRes();
        Fee feeData = new Fee();
        feeData.setCurrency("LAK");
        feeData.setFee(BigDecimal.valueOf(0));
        result.setStatus("success");
        result.setData(feeData);
       return result;
    }







}
