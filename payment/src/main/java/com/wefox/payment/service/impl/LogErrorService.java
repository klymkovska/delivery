package com.wefox.payment.service.impl;

import com.wefox.payment.dto.Error;
import com.wefox.payment.service.ILogErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class LogErrorService implements ILogErrorService {

    @Value(value = "${payment.error.log.endpoint}")
    private String errorLogEndpoint;
    @Autowired
    private RestTemplate errorLogRestTemplate;

    @Override
    public boolean logError(Error error) {
        HttpEntity<Error> entity = new HttpEntity<>(error);
        try {
            log.debug("Error log request for payment {}", error.getPaymentId());
            ResponseEntity<Error> response = errorLogRestTemplate.postForEntity(errorLogEndpoint, entity, Error.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            log.error("Error log request for payment {} has failed", error.getPaymentId());
        }
        return false;
    }
}
