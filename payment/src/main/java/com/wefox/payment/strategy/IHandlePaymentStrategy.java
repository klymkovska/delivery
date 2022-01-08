package com.wefox.payment.strategy;

import com.wefox.payment.dto.PaymentMessage;

public interface IHandlePaymentStrategy {

    void handlePayment(PaymentMessage message);
}
