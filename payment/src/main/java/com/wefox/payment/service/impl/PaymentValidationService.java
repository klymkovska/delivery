package com.wefox.payment.service.impl;

import com.wefox.payment.converter.impl.PaymentValidationConverter;
import com.wefox.payment.dto.Error;
import com.wefox.payment.dto.PaymentMessage;
import com.wefox.payment.dto.PaymentValidation;
import com.wefox.payment.service.ILogErrorService;
import com.wefox.payment.service.IPaymentValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class PaymentValidationService implements IPaymentValidationService {

    private final static String NETWORK_ERROR_TYPE = "network";
    private final static String CLIENT_ERROR_TYPE = "client";
    private final static String SERVER_ERROR_TYPE = "server";

    @Value(value = "${payment.validation.endpoint}")
    private String paymentValidationEndpoint;
    @Autowired
    private PaymentValidationConverter paymentValidationConverter;
    @Autowired
    private RestTemplate paymentValidationRestTemplate;
    @Autowired
    private ILogErrorService logErrorService;

    @Override
    public boolean validatePayment(PaymentMessage paymentMessage) {
        HttpEntity<PaymentValidation> entity = new HttpEntity<>(paymentValidationConverter.convert(paymentMessage));
        try {
            log.debug("Validation request for payment {}", paymentMessage.getPaymentId());
            ResponseEntity<Void> response = paymentValidationRestTemplate.postForEntity(paymentValidationEndpoint, entity, Void.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.error("Validation request for payment {} has failed with status code {}", paymentMessage.getPaymentId(), response.getStatusCode().value());
                Error error = buildErrorRequestByStatus(paymentMessage, response);
                logErrorService.logError(error);
            }
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Validation request for payment {} has failed", paymentMessage.getPaymentId());
            Error error = buildErrorRequest(paymentMessage);
            logErrorService.logError(error);
        }
        return false;
    }

    private Error buildErrorRequest(PaymentMessage paymentMessage) {
        Error error = new Error();
        error.setPaymentId(paymentMessage.getPaymentId());
        error.setErrorType(NETWORK_ERROR_TYPE);
        error.setErrorDescription("Validation service is unavailable");
        return error;
    }

    private Error buildErrorRequestByStatus(PaymentMessage paymentMessage, ResponseEntity<Void> response) {
        Error error = new Error();
        error.setPaymentId(paymentMessage.getPaymentId());
        switch (response.getStatusCode().series()) {
            case CLIENT_ERROR:
                error.setErrorType(CLIENT_ERROR_TYPE);
                break;
            case SERVER_ERROR:
                error.setErrorType(SERVER_ERROR_TYPE);
                break;
        }
        error.setErrorDescription(response.getStatusCode().getReasonPhrase());
        return error;
    }
}
