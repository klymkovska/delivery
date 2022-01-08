package com.wefox.payment.converter.impl;

import com.wefox.payment.converter.IConverter;
import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.model.Payment;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.Instant;

@Component
public class PaymentConverter implements IConverter<PaymentMessage, Payment> {

    @Override
    public Payment convert(PaymentMessage message) {
        Assert.notNull(message, "Message can not be null");
        Payment model = new Payment();
        model.setPaymentId(message.getPaymentId());
        model.setAccountId(message.getAccountId());
        model.setPaymentType(message.getPaymentType());
        model.setAmount(message.getAmount());
        model.setCreditCard(message.getCreditCard());
        //todo: how does the delay impact this field
        model.setCreatedOn(Timestamp.from(Instant.now()));
        return model;
    }
}
