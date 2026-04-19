package com.lbb.customer.buygold.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String product_name;
    private String product_id;
    private BigDecimal  quantity;
    private String unit;
    private BigDecimal amount;
    private String image_url;
}
