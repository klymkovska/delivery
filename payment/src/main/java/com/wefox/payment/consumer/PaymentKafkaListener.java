package com.wefox.payment.consumer;

import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.strategy.impl.HandleOfflinePaymentStrategy;
import com.wefox.payment.strategy.impl.HandleOnlinePaymentStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentKafkaListener {

    @Autowired
    private HandleOfflinePaymentStrategy handleOfflinePaymentStrategy;
    @Autowired
    private HandleOnlinePaymentStrategy handleOnlinePaymentStrategy;

    @KafkaListener(topics = "${kafka.offline.topic}", groupId = "${kafka.group.id}", containerFactory = "paymentKafkaListenerContainerFactory")
    public void listenOfflinePaymentMessage(PaymentMessage message) {
        log.info("Received Message: " + message);
        handleOfflinePaymentStrategy.handlePayment(message);
    }

    @KafkaListener(topics = "${kafka.online.topic}", groupId = "${kafka.group.id}", containerFactory = "paymentKafkaListenerContainerFactory")
    public void listenOnlinePaymentMessage(PaymentMessage message) {
        log.info("Received Message: " + message);
        handleOnlinePaymentStrategy.handlePayment(message);
    }
}
