package com.lbb.customer.createaccount.db.service;

import com.lbb.customer.createaccount.db.entity.TdInfoEntity;
import com.lbb.customer.createaccount.db.entity.TmpAccountEntity;
import com.lbb.customer.createaccount.db.mapper.TDMapper;
import com.lbb.customer.createaccount.db.mapper.TempTDAccMapper;
import com.lbb.customer.createaccount.db.repository.TdRepository;
import com.lbb.customer.createaccount.db.repository.TempTDAccRepository;
import com.lbb.customer.createaccount.model.GetMonth;
import com.lbb.customer.createaccount.model.TempTDAcc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TdServiceImp implements TdService{
    private static final Logger logger = LogManager.getLogger(TdServiceImp.class);
    private final TdRepository tdRepository;
    private final TDMapper tdMapper;
    private final TempTDAccRepository tempTDAccRepository;
    private final TempTDAccMapper tempTDAccMapper;

    public TdServiceImp(TdRepository tdRepository, TDMapper tdMapper, TempTDAccRepository tempTDAccRepository, TempTDAccMapper tempTDAccMapper) {
        this.tdRepository = tdRepository;
        this.tdMapper = tdMapper;
        this.tempTDAccRepository = tempTDAccRepository;
        this.tempTDAccMapper = tempTDAccMapper;
    }

    @Override
    public List<GetMonth> getAllProductTD() {

        List<TdInfoEntity> entities = tdRepository.getAllTD();
        return tdMapper.toTDResponseList(entities);
    }

    @Override
    public List<GetMonth> getProductByCode(String code) {
        List<TdInfoEntity> entities = tdRepository.getTDByCode(code);
        return tdMapper.toTDResponseList(entities);
    }

    @Override
    public List<TempTDAcc> getTempAccountByRef(String ref) {
        List<TmpAccountEntity> entities = tempTDAccRepository.getTempAccByRef(ref);
        return tempTDAccMapper.toTempTDAccResponseList(entities);
    }

    @Override
    public List<TempTDAcc> getTempAccountInqByRef(String ref) {
        List<TmpAccountEntity> entities = tempTDAccRepository.getTempAccInqByRef(ref);
        return tempTDAccMapper.toTempTDAccResponseList(entities);
    }

    @Override
    public String StoreTempTDAccount(TempTDAcc data) {

        TmpAccountEntity entity = new TmpAccountEntity();

        try {
            entity.setReferent(data.getReferent());
            entity.setCustomerId(data.getCustomerId());
            entity.setAccountNo(data.getAccountNo());
            entity.setAccountType(data.getAccountType());
            entity.setAccountName(data.getAccountName());
            entity.setAccountCurrency(data.getAccountCurrency());
            entity.setDescription(data.getDescription());
            entity.setStatus(data.getStatus());
            entity.setCreatedAt(data.getCreatedAt());
            entity.setUpdatedAt(data.getUpdatedAt());
            entity.setBalance(data.getBalance());
            entity.setRate(data.getRate());
            entity.setStartDate(data.getStartDate());
            entity.setEndDate(data.getEndDate());
            entity.setRateAmount(data.getRateAmount());
            entity.setTotalAmount(data.getTotalAmount());
            entity.setMonth(data.getMonth());

            TmpAccountEntity saved =   tempTDAccRepository.save(entity);
            logger.info("SAVE SUCCESS: " + saved.getId());
            return "Success";

        } catch (Exception e) {

            logger.info("INSERT FAILED: " + e.getMessage());
            return "Fail";
        }
    }

    @Override
    public Boolean UpdateTempTDAccount(String ref) {
        int updatedRows = tempTDAccRepository.updateTDInq(ref);
        return updatedRows > 0;
    }


}
