package com.lbb.customer.createaccount.db.service;

import com.lbb.customer.buygold.service.ListProductService;
import com.lbb.customer.createaccount.db.entity.TdInfoEntity;
import com.lbb.customer.createaccount.db.mapper.TDMapper;
import com.lbb.customer.createaccount.db.repository.TdRepository;
import com.lbb.customer.createaccount.model.GetMonth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TdServiceImp implements TdService{
    private static final Logger logger = LogManager.getLogger(TdServiceImp.class);
    private final TdRepository tdRepository;
    private final TDMapper tdMapper;

    public TdServiceImp(TdRepository tdRepository, TDMapper tdMapper) {
        this.tdRepository = tdRepository;
        this.tdMapper = tdMapper;
    }

    @Override
    public List<GetMonth> getAllProductTD() {

        List<TdInfoEntity> entities = tdRepository.getAllTD();
        return tdMapper.toTDResponseList(entities);
    }
}
