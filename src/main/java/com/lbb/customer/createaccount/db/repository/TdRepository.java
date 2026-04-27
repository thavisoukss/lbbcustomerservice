package com.lbb.customer.createaccount.db.repository;

import com.lbb.customer.buygold.db.entity.ProductEntity;
import com.lbb.customer.createaccount.db.entity.TdInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TdRepository extends JpaRepository<TdInfoEntity, Long> {

    @Query(value = "SELECT ID, TD_CODE, INTEREST_TYPE, ACCOUNT_TYPE, EFFECT_DATE, DESCRIPTION, ADD_DAY, CCY, " +
            "TIME_PERIOD, TIME_UNIT, STATUS FROM ADMIN_MBUAT.TD_INFO WHERE STATUS = 'A'  " , nativeQuery = true)
    List<TdInfoEntity> getAllTD();

    @Query(value = "SELECT ID, TD_CODE, INTEREST_TYPE, ACCOUNT_TYPE, EFFECT_DATE, DESCRIPTION, ADD_DAY, CCY, " +
            "TIME_PERIOD, TIME_UNIT, STATUS FROM ADMIN_MBUAT.TD_INFO WHERE STATUS = 'A' AND TD_CODE =:tdCode " , nativeQuery = true)
    List<TdInfoEntity> getTDByCode(@Param("tdCode") String tdCode);

}
