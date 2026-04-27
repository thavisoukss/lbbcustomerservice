package com.lbb.customer.createaccount.db.service;

import com.lbb.customer.createaccount.model.GetMonth;
import com.lbb.customer.createaccount.model.TempTDAcc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TdService {
    List<GetMonth> getAllProductTD ();
    List<GetMonth> getProductByCode (String code);
    List<TempTDAcc> getTempAccountByRef(String ref);
    List<TempTDAcc> getTempAccountInqByRef(String ref);
    String StoreTempTDAccount(TempTDAcc data);
    Boolean UpdateTempTDAccount(String ref);
}
