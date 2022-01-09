package com.wefox.payment.strategy;

import com.wefox.payment.converter.impl.PaymentConverter;
import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.model.Payment;
import com.wefox.payment.repository.AccountRepository;
import com.wefox.payment.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractHandlePaymentStrategy {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PaymentConverter paymentConverter;

    public void handlePayment(PaymentMessage message) {
        Payment paymentModel = paymentConverter.convert(message);
        try {
            paymentRepository.save(paymentModel);
            accountRepository.updateLastPaymentDate(paymentModel.getCreatedOn(), paymentModel.getAccountId());
        } catch (HibernateException e) {
            log.error("Error occurred while persisting a payment");
        }
    }
}
