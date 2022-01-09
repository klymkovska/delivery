package com.wefox.payment.service;

import com.wefox.payment.dto.PaymentMessage;

public interface IPaymentValidationService {

    boolean validatePayment(PaymentMessage paymentMessage);
}
