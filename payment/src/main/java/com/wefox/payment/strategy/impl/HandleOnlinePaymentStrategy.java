package com.wefox.payment.strategy.impl;

import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.strategy.IHandlePaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class HandleOnlinePaymentStrategy implements IHandlePaymentStrategy {
    @Override
    public void handlePayment(PaymentMessage message) {

    }
}
