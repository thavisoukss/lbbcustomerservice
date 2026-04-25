package com.lbb.customer.statement.db.service;

import com.lbb.customer.statement.model.listaccount.ListAccountData;
import com.lbb.customer.statement.model.listaccount.StoreAccountTd;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ListAccountService {
    List<ListAccountData> getListTDAccount(String userId);
    List<ListAccountData> getListCurrentAccount(String userId);

    String StoreAccountTD (StoreAccountTd data);


}
