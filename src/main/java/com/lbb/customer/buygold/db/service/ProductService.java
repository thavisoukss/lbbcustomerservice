package com.lbb.customer.buygold.db.service;

import com.lbb.customer.buygold.model.ProductData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService  {
    List<ProductData> getAllProduct();
}
