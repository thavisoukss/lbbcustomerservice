package com.lbb.customer.createaccount.db.mapper;

import com.lbb.customer.buygold.db.entity.ProductEntity;
import com.lbb.customer.buygold.model.ProductData;
import com.lbb.customer.createaccount.db.entity.TdInfoEntity;
import com.lbb.customer.createaccount.model.GetMonth;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TDMapper {
    public GetMonth toTDResponse (TdInfoEntity entity){
        if (entity == null) {
            return null;
        }

        GetMonth data = new GetMonth();
        data.setTd_code(entity.getTdCode());
        data.setDescription(entity.getDescription());
        data.setInterest_type(entity.getInterestType());
        return data;
    }


    public List<GetMonth> toTDResponseList(List<TdInfoEntity> entities) {
        List<GetMonth> responses = new ArrayList<>();

        if (entities == null || entities.isEmpty()) {
            return responses;
        }
        for (TdInfoEntity entity : entities) {
            responses.add(toTDResponse(entity));
        }
        return responses;
    }


}
