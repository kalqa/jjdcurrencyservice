package com.currency.currencyservice.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.currency.currencyservice.service.exception.CurrencyNotFoundException;

public class SimpleCurrencyService implements CurrencyService {

    private static final Map<String, Double> currencies = new HashMap<>();

    public SimpleCurrencyService() {
        currencies.put("EUR", 4.55);
    }

    @Override
    public Double getCurrencyValueByCurrencyCode(String code) {
        String currencyCode = findCurrencyCodeInCurrencies(code)
                .orElseThrow(() -> new CurrencyNotFoundException(code));
        return currencies.get(currencyCode);
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