package com.lbb.customer.createaccount.db.mapper;

import com.lbb.customer.createaccount.db.entity.TdInfoEntity;
import com.lbb.customer.createaccount.db.entity.TmpAccountEntity;
import com.lbb.customer.createaccount.model.GetMonth;
import com.lbb.customer.createaccount.model.TempTDAcc;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TempTDAccMapper {
    public TempTDAcc toTempTDAccResponse (TmpAccountEntity entity){
        if (entity == null) {
            return null;
        }

        TempTDAcc data = new TempTDAcc();
        data.setId(entity.getId());
        data.setReferent(entity.getReferent());
        data.setCustomerId(entity.getCustomerId());
        data.setAccountNo(entity.getAccountNo());
        data.setAccountName(entity.getAccountName());
        data.setAccountType(entity.getAccountType());
        data.setAccountCurrency(entity.getAccountCurrency());
        data.setDescription(entity.getDescription());
        data.setStatus(entity.getStatus());
        data.setCreatedAt(entity.getCreatedAt());
        data.setUpdatedAt(entity.getUpdatedAt());
        data.setDeleteAt(entity.getDeleteAt());
        data.setBalance(entity.getBalance());
        data.setMigrateStatus(entity.getMigrateStatus());
        data.setMonth(entity.getMonth());
        data.setTotalAmount(entity.getTotalAmount());
        data.setRate(entity.getRate());
        return data;
    }

    public List<TempTDAcc> toTempTDAccResponseList(List<TmpAccountEntity> entities) {
        List<TempTDAcc> responses = new ArrayList<>();

        if (entities == null || entities.isEmpty()) {
            return responses;
        }
        for (TmpAccountEntity entity : entities) {
            responses.add(toTempTDAccResponse(entity));
        }
        return responses;
    }


}
