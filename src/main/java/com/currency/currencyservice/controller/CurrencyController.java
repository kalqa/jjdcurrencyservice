package com.currency.currencyservice.controller;

import com.currency.currencyservice.model.CurrencyResponse;
import com.currency.currencyservice.service.CurrencyService;
import com.currency.currencyservice.service.exception.CurrencyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{code}")
    @ResponseBody
    public CurrencyResponse getCurrencyValue(@PathVariable String code) {
        return currencyService.getCurrencyValueByCurrencyCode(code);
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<String> handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
        String message = ex.getMessage();
        logger.error("ErrorMessage: {} , exceptionBody: {}", message, ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}