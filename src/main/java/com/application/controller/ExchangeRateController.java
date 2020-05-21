package com.application.controller;

import com.application.Entity.ExchangeRate;
import com.application.service.ExchangeRateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExchangeRateController {
	private final ExchangeRateService exchangeRateService;

	@ApiOperation(value = "Установить курс обмена валют по валютной паре. Курс обртаной пары должен быть установлен автоматически.", response = ExchangeRate.class, authorizations = {
			@Authorization(value = "basicAuth")}, tags = {"exchange-rates"})

	@PostMapping(path = "/exchange-rates")
	public ExchangeRate setExchangeRate(@Valid @RequestBody ExchangeRate exchangeRate) {
		return exchangeRateService.saveExchangeRate(exchangeRate);
	}

	@ApiOperation(value = "Получить все курсы обмена валют.", authorizations = {
			@Authorization(value = "basicAuth")}, tags = {"exchange-rates"})
	@GetMapping(path = "/exchange-rates")
	List<ExchangeRate> getAllExchangeRates() {
		return exchangeRateService.getExchangeRates();
	}
}
