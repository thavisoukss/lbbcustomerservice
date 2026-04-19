package com.lbb.customer.buygold.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductData {

    private Long id;

    private String productName;

    private String description;

    private Integer quantity;


    private String unit;

    private String status;

    private String createdAt;

    private String updatedAt;

    private String deleteAt;

    private String image;
}
