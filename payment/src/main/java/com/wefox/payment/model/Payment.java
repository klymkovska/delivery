package com.wefox.payment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    @Id
    @Column(name = "payment_id")
    @NotNull(message = "Payment id must not be null")
    private String paymentId;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "payment_type")
    @NotNull(message = "Payment type must not be null")
    private String paymentType;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "amount")
    @NotNull(message = "Payment amount must not be null")
    private Double amount;

    @Column(name = "created_on")
    private Timestamp createdOn;
}
