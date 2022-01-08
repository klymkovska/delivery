package com.wefox.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PaymentMessage {

    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("account_id")
    private Integer accountId;
    @JsonProperty("payment_type")
    private String paymentType;
    @JsonProperty("credit_card")
    private String creditCard;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("delay")
    private Integer delay;
}
