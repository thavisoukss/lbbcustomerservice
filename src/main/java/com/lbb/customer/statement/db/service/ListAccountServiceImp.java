package com.lbb.customer.statement.db.service;

import com.lbb.customer.statement.db.entity.ListAccountEntity;
import com.lbb.customer.statement.db.mapper.ListAccountMapper;
import com.lbb.customer.statement.db.repository.ListAccountRepository;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import com.lbb.customer.statement.model.listaccount.StoreAccountTd;
import com.lbb.customer.statement.service.StatementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListAccountServiceImp implements ListAccountService {
    private static final Logger logger = LogManager.getLogger(ListAccountServiceImp.class);

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

    @Override
    public List<ListAccountData> getListCurrentAccount(String userId) {

        List<ListAccountEntity> entities = listAccountRepository.getListCurrAccount(userId);
        return listAccountMapper.toAccResponseList(entities);
    }

    @Override
    public String StoreAccountTD(StoreAccountTd data) {

        ListAccountEntity entity = new ListAccountEntity();

        try {
        entity.setCustomerId(data.getCustomerId());
        entity.setAccountNo(data.getAccountNo());
        entity.setAccName(data.getAccName());
        entity.setAccountType(data.getAccountType());
        entity.setAccountCurrency(data.getAccountCcy());
        entity.setDescription(data.getDescription());
        entity.setStatus(data.getStatus());
        entity.setCreatedAt(data.getCreateAt());
        entity.setUpdatedAt(data.getUpdateAt());
        entity.setBalance(data.getBalance());

            ListAccountEntity saved =   listAccountRepository.save(entity);
            logger.info("SAVE SUCCESS: " + saved.getId());
            return "Success";

        } catch (Exception e) {

            logger.info("INSERT FAILED: " + e.getMessage());
            return "Fail";
        }
    }


}
