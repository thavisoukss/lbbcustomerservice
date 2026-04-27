package com.lbb.customer.createaccount.db.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TMP_ACCOUNT", schema = "ADMIN_MBUAT")
@Data
public class TmpAccountEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or SEQUENCE if Oracle older version
    private Long id;

    @Column(name = "REFERENT", length = 100)
    private String referent;

    @Column(name = "CUSTOMER_ID", length = 50)
    private String customerId;

    @Column(name = "ACCOUNT_NO", nullable = false, length = 50)
    private String accountNo;

    @Column(name = "ACCOUNT_NAME", length = 100)
    private String accountName;

    @Column(name = "ACCOUNT_TYPE", length = 50)
    private String accountType;

    @Column(name = "ACCOUNT_CURRENCY", length = 10)
    private String accountCurrency;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @Column(name = "STATUS", length = 20)
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "DELETE_AT")
    private LocalDateTime deleteAt;

    private BigDecimal balance;

    private String migrateStatus;

    // 🔽 NEW FIELDS

    @Column(name = "RATE")
    private BigDecimal rate;

    @Column(name = "MONTH")
    private int month;

    @Column(name = "START_DATE")
    private String startDate;

    @Column(name = "END_DATE")
    private String endDate;

    @Column(name = "RATE_AMOUNT")
    private BigDecimal rateAmount;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

}
