package com.wefox.payment.service;

import com.wefox.payment.dto.Error;

public interface ILogErrorService {

    boolean logError(Error error);
}
