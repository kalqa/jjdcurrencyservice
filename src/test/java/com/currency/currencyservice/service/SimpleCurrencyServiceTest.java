package com.currency.currencyservice.service;

import com.currency.currencyservice.service.exception.CurrencyNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleCurrencyServiceTest {

    final CurrencyService simpleCurrencyService = new SimpleCurrencyService();

    @Test
    void should_return_correct_currency_value_when_eur_code_given() {
        String givenCurrencyCode = "EUR";
        Double expectedCurrencyValue = 4.55;

        Double actualCurrencyValue = simpleCurrencyService.getCurrencyValueByCurrencyCode(givenCurrencyCode);

        assertEquals(expectedCurrencyValue, actualCurrencyValue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"xaz", "USD", "", "ADASDcc"})
    void should_throw_CurrencyNotFoundException_when_wrong_currency_code_given(String currencyCode) {
        assertThrows(CurrencyNotFoundException.class, () -> simpleCurrencyService.getCurrencyValueByCurrencyCode(currencyCode));
    }
}