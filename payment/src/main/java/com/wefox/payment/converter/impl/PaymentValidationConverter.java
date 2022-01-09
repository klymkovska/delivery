package com.wefox.payment.converter.impl;

import com.wefox.payment.converter.IConverter;
import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.dto.PaymentValidation;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class PaymentValidationConverter implements IConverter<PaymentMessage, PaymentValidation> {

    @Override
    public PaymentValidation convert(PaymentMessage message) {
        Assert.notNull(message, "Message can not be null");
        PaymentValidation paymentValidation = new PaymentValidation();
        paymentValidation.setPaymentId(message.getPaymentId());
        paymentValidation.setAccountId(message.getAccountId());
        paymentValidation.setAmount(message.getAmount());
        paymentValidation.setCreditCard(message.getCreditCard());
        return paymentValidation;
    }
}
