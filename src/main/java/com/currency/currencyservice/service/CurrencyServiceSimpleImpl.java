package com.currency.currencyservice.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.currency.currencyservice.model.CurrencyResponse;
import com.currency.currencyservice.service.exception.CurrencyNotFoundException;

public class CurrencyServiceSimpleImpl implements CurrencyService {

    private static final Map<String, Double> currencies = new HashMap<>();

    public CurrencyServiceSimpleImpl() {
        currencies.put("EUR", 4.55);
    }

    @Override
    public CurrencyResponse getCurrencyValueByCurrencyCode(String code) throws CurrencyNotFoundException {
        String currencyCode = findCurrencyCodeInCurrencies(code)
                .orElseThrow(() -> new CurrencyNotFoundException(code));
        Double currencyValue = currencies.get(currencyCode);
        return new CurrencyResponse(currencyValue);
    }

    private Optional<String> findCurrencyCodeInCurrencies(String code) {
        return currencies.keySet()
                .stream()
                .filter(areCurrencyCodesEqual(code))
                .findFirst();
    }

    private Predicate<String> areCurrencyCodesEqual(String code) {
        return currency -> currency.equals(code);
    }
}