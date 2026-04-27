package com.lbb.customer.createaccount.db.repository;

import com.lbb.customer.createaccount.db.entity.TdInfoEntity;
import com.lbb.customer.createaccount.db.entity.TmpAccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TempTDAccRepository extends JpaRepository<TmpAccountEntity, Long> {

    @Query(value = "SELECT ID, REFERENT, CUSTOMER_ID, ACCOUNT_NO, ACCOUNT_NAME, ACCOUNT_TYPE, ACCOUNT_CURRENCY, DESCRIPTION, \n" +
            "STATUS, CREATED_AT, UPDATED_AT, DELETE_AT, BALANCE, MIGRATE_STATUS, RATE, START_DATE, END_DATE, RATE_AMOUNT,\n" +
            "TOTAL_AMOUNT , MONTH FROM ADMIN_MBUAT.TMP_ACCOUNT WHERE STATUS = 'CAL' AND  REFERENT =  :ref  " , nativeQuery = true)
    List<TmpAccountEntity> getTempAccByRef(@Param("ref") String ref);

    @Query(value = "SELECT ID, REFERENT, CUSTOMER_ID, ACCOUNT_NO, ACCOUNT_NAME, ACCOUNT_TYPE, ACCOUNT_CURRENCY, DESCRIPTION, \n" +
            "STATUS, CREATED_AT, UPDATED_AT, DELETE_AT, BALANCE, MIGRATE_STATUS, RATE, START_DATE, END_DATE, RATE_AMOUNT,\n" +
            "TOTAL_AMOUNT , MONTH FROM ADMIN_MBUAT.TMP_ACCOUNT WHERE STATUS = 'INQ' AND  REFERENT =  :ref  " , nativeQuery = true)
    List<TmpAccountEntity> getTempAccInqByRef(@Param("ref") String ref);


    @Modifying
    @Transactional
    @Query(value = "UPDATE  ADMIN_MBUAT.TMP_ACCOUNT SET STATUS = 'INQ'  WHERE REFERENT =:ref  " , nativeQuery = true)
    int updateTDInq (@Param("ref") String ref);

}
