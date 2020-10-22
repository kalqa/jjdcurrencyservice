package com.currency.currencyservice.controller;

import com.currency.currencyservice.model.CurrencyResponse;
import com.currency.currencyservice.service.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/currency")
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{code}")
    @ResponseBody
    public CurrencyResponse getCurrencyValue(@PathVariable String code) {
        return currencyService.getCurrencyValueByCurrencyCode(code);
    }
}