package com.lbb.customer.statement.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "ACCOUNT", schema = "MB_UAT")
public class ListAccountEntity {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "ACCOUNT_NO", length = 50)
    private String accountNo;

    @Column(name = "ACCOUNT_NAME", length = 255)
    private String accountName;

    @Column(name = "ACCOUNT_TYPE", length = 50)
    private String accountType;

    @Column(name = "ACCOUNT_CURRENCY", length = 10)
    private String accountCurrency;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private String createdAt;

    @Column(name = "UPDATED_AT")
    private String updatedAt;

    @Column(name = "DELETE_AT")
    private String deleteAt;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "MIGRATE_STATUS")
    private String migrateStatus;
}
