package com.lbb.customer.statement.db.service;

import com.lbb.customer.statement.db.entity.ListAccountEntity;
import com.lbb.customer.statement.db.mapper.ListAccountMapper;
import com.lbb.customer.statement.db.repository.ListAccountRepository;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListAccountServiceImp implements ListAccountService {

    private final ListAccountRepository  listAccountRepository;
    private final ListAccountMapper listAccountMapper;

    public ListAccountServiceImp(ListAccountRepository listAccountRepository, ListAccountMapper listAccountMapper) {
        this.listAccountRepository = listAccountRepository;
        this.listAccountMapper = listAccountMapper;
    }

    @Override
    public List<ListAccountData> getListTDAccount(String userId) {

        List<ListAccountEntity> entities = listAccountRepository.getListTDAccount(userId);
        return listAccountMapper.toAccResponseList(entities);
    }


}
