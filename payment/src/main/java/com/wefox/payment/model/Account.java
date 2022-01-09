package com.wefox.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "account_id")
    @NotNull(message = "Account id must not be null")
    private Long accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @NotNull(message = "Email must not be null")
    private String email;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "last_payment_date")
    private Timestamp lastPaymentDate;

    @Column(name = "created_on")
    private Timestamp createdOn;
}
