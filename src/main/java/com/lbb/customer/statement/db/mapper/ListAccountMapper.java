package com.lbb.customer.statement.db.mapper;

import com.lbb.customer.statement.db.entity.ListAccountEntity;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListAccountMapper {

    public ListAccountData  toAccResponse (ListAccountEntity entity){
        if (entity == null) {
            return null;
        }

        ListAccountData data = new ListAccountData();
        data.setTransaction_id("");
        data.setAccount_number(entity.getAccountNo());
        data.setAccount_name(entity.getAccName());
        data.setAccount_type(entity.getAccountType());
        data.setBalance(entity.getBalance());
        data.setCurrency(entity.getAccountCurrency());
        data.setCreated_at(String.valueOf(entity.getCreatedAt()));
        data.setStatus(entity.getStatus());
        return data;
    }

    public List<ListAccountData> toAccResponseList(List<ListAccountEntity> entities) {
        List<ListAccountData> responses = new ArrayList<>();

        if (entities == null || entities.isEmpty()) {
            return responses;
        }

        for (ListAccountEntity entity : entities) {
            responses.add(toAccResponse(entity));
        }
        return responses;
    }


}
