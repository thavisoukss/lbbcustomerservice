package com.lbb.customer.createaccount.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempTDAcc {
    private Long id;
    private String referent;
    private String customerId;
    private String accountNo;
    private String accountName;
    private String accountType;
    private String accountCurrency;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deleteAt;
    private BigDecimal balance;
    private String migrateStatus;

    private BigDecimal rate;

    private String startDate;

    private String endDate;
    private int month;

    private BigDecimal rateAmount;

    private BigDecimal totalAmount;

}
