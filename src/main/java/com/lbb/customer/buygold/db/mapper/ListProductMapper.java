package com.lbb.customer.buygold.db.mapper;

import com.lbb.customer.buygold.db.entity.ProductEntity;
import com.lbb.customer.buygold.model.Product;
import com.lbb.customer.buygold.model.ProductData;
import com.lbb.customer.statement.db.entity.ListAccountEntity;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListProductMapper {

    public ProductData  toProductResponse (ProductEntity entity){
        if (entity == null) {
            return null;
        }

        ProductData data = new ProductData();
        data.setId(entity.getId());
        data.setProductName(entity.getProductName());
        data.setImage(entity.getImage());
        data.setQuantity(entity.getQuantity());
        data.setUnit(entity.getUnit());
        data.setStatus(entity.getStatus());
        data.setCreatedAt(entity.getCreatedAt());
        return data;
    }


    public List<ProductData> toProductResponseList(List<ProductEntity> entities) {
        List<ProductData> responses = new ArrayList<>();

        if (entities == null || entities.isEmpty()) {
            return responses;
        }

        for (ProductEntity entity : entities) {
            responses.add(toProductResponse(entity));
        }
        return responses;
    }


}
