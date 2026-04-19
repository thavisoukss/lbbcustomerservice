package com.lbb.customer.buygold.db.repository;

import com.lbb.customer.buygold.db.entity.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT ID, PRODUCT_NAME, DESCRIPTION, QUANTITY, UNIT, STATUS, CREATED_AT, UPDATED_AT, DELETE_AT, IMAGE\n" +
            "FROM ADMIN_MBUAT.PRODUCT where STATUS ='ACTIVE' " , nativeQuery = true)
    List<ProductEntity> getAllProduct();

}
