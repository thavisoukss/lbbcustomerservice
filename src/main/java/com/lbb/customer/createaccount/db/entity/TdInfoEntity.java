package com.lbb.customer.createaccount.db.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TD_INFO", schema = "ADMIN_MBUAT")
public class TdInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or SEQUENCE (Oracle preferred)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TD_CODE")
    private String tdCode;

    @Column(name = "INTEREST_TYPE")
    private String interestType;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "EFFECT_DATE")
    private String effectDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADD_DAY")
    private Integer addDay;

    @Column(name = "CCY")
    private String ccy;

    @Column(name = "TIME_PERIOD")
    private Integer timePeriod;

    @Column(name = "TIME_UNIT")
    private String timeUnit;

    @Column(name = "STATUS")
    private String status;
}
