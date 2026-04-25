package com.lbb.customer.statement.model.listaccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreAccountTd {
    private String customerId;
    private String accountNo;
    private String accName;
    private String accountType;
    private String accountCcy;
    private String description;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
    private BigDecimal balance;
    private String migrateStatus;
}
