package com.wefox.payment.strategy.impl;

import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.service.IPaymentValidationService;
import com.wefox.payment.strategy.AbstractHandlePaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HandleOnlinePaymentStrategy extends AbstractHandlePaymentStrategy {

    @Autowired
    private IPaymentValidationService paymentValidationService;

    @Override
    public void handlePayment(PaymentMessage message) {
        super.handlePayment(message);
        paymentValidationService.validatePayment(message);
    }
}
