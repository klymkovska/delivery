package com.wefox.payment.strategy.impl;

import com.wefox.payment.converter.impl.PaymentConverter;
import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.model.Payment;
import com.wefox.payment.repository.PaymentRepository;
import com.wefox.payment.strategy.IHandlePaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HandleOfflinePaymentStrategy implements IHandlePaymentStrategy {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentConverter paymentConverter;

    @Override
    public void handlePayment(PaymentMessage message) {
        Payment paymentModel = paymentConverter.convert(message);
        try {
            paymentRepository.save(paymentModel);
        } catch (HibernateException  e) {
            log.error("Error occurred while persisting a payment");
        }
    }
}
