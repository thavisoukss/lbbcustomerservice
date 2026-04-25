package com.lbb.customer.statement.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ACCOUNT", schema = "MB_UAT")
public class ListAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "ID")
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "ACCOUNT_NO", length = 50)
    private String accountNo;

    @Column(name = "ACCOUNT_NAME")
    private String accName;

    @Column(name = "ACCOUNT_TYPE", length = 50)
    private String accountType;

    @Column(name = "ACCOUNT_CURRENCY", length = 10)
    private String accountCurrency;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "DELETE_AT")
    private LocalDateTime deleteAt;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "MIGRATE_STATUS")
    private String migrateStatus;
}
