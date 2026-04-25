package com.lbb.customer.createaccount.db.service;

import com.lbb.customer.createaccount.model.GetMonth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TdService {
    List<GetMonth> getAllProductTD ();
}
