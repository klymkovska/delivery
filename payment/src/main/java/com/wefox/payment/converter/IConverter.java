package com.wefox.payment.converter;

public interface IConverter<S, T> {

    T convert(S model);
}
