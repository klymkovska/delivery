package com.wefox.payment.consumer;

import com.wefox.payment.dto.PaymentMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OfflinePaymentKafkaListener {

    @KafkaListener(topics = "${kafka.offline.topic}", groupId = "${kafka.group.id}", containerFactory = "paymentKafkaListenerContainerFactory")
    public void listenOfflinePaymentMessage(PaymentMessage message) {
        log.info("Received Message: " + message);
    }
}
