package com.application.service;

import com.application.Entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {

	ExchangeRate saveExchangeRate(ExchangeRate exchangeRate);

	List<ExchangeRate> getExchangeRates();
}
