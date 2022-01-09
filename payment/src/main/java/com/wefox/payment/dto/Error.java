package com.wefox.payment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Error {
    @JsonProperty("payment_id")
    private String paymentId;
    @JsonProperty("error_type")
    private String errorType;
    @JsonProperty("error_description")
    private String errorDescription;
}
