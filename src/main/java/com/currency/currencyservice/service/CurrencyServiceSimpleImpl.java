package com.currency.currencyservice.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import com.currency.currencyservice.model.CurrencyResponse;
import com.currency.currencyservice.service.error.CurrencyApiError;
import com.currency.currencyservice.service.exception.CurrencyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class CurrencyServiceSimpleImpl implements CurrencyService {

    private static final Map<String, Double> currencies = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceSimpleImpl.class);

    public CurrencyServiceSimpleImpl() {
        currencies.put("EUR", 4.55);
    }

    @Override
    public CurrencyResponse getCurrencyValueByCurrencyCode(String code) {
        String currencyCode = findCurrencyCodeInCurrencies(code)
                .orElseThrow(() -> new CurrencyNotFoundException(code));
        Double currencyValue = currencies.get(currencyCode);
        return new CurrencyResponse(currencyValue);
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public CurrencyApiError handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
        String message = ex.getMessage();
        logger.error("ErrorMessage: {} , exceptionBody: {}", message, ex);
        return new CurrencyApiError(HttpStatus.BAD_REQUEST, message, ex);
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