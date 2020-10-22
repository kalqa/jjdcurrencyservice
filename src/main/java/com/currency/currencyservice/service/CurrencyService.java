package com.currency.currencyservice.service;

import com.currency.currencyservice.model.CurrencyResponse;

public interface CurrencyService {

    CurrencyResponse getCurrencyValueByCurrencyCode(String code);
}
