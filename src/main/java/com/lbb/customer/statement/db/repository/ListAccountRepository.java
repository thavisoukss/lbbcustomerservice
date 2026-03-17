package com.lbb.customer.statement.db.repository;

import com.lbb.customer.statement.db.entity.ListAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListAccountRepository extends JpaRepository<ListAccountEntity, Long> {

    @Query(value = "SELECT ID, CUSTOMER_ID, ACCOUNT_NO, ACCOUNT_NAME, ACCOUNT_TYPE, ACCOUNT_CURRENCY, DESCRIPTION, STATUS," +
            " CREATED_AT, UPDATED_AT, DELETE_AT, BALANCE, MIGRATE_STATUS FROM MB_UAT.ACCOUNT" +
            " WHERE STATUS ='ACTIVE'  AND DESCRIPTION ='Term Deposit Account' AND CUSTOMER_ID = :userId " , nativeQuery = true)
    List<ListAccountEntity> getListTDAccount(@Param("userId") String userId);

    @Query(value = "SELECT ID, CUSTOMER_ID, ACCOUNT_NO, ACCOUNT_NAME, ACCOUNT_TYPE, ACCOUNT_CURRENCY, DESCRIPTION, STATUS," +
            " CREATED_AT, UPDATED_AT, DELETE_AT, BALANCE, MIGRATE_STATUS FROM MB_UAT.ACCOUNT" +
            " WHERE STATUS ='ACTIVE'  AND DESCRIPTION ='Current Account' AND CUSTOMER_ID = :userId " , nativeQuery = true)
    List<ListAccountEntity> getListCurrAccount(@Param("userId") String userId);


}
