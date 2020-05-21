package com.application.service.impl;

import com.application.Entity.ExchangeRate;
import com.application.repository.ExchangeRateRepository;
import com.application.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
	private final ExchangeRateRepository exchangeRateRepository;

	@Override
	public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) {
		return exchangeRateRepository.save(exchangeRate);
	}

	@Override
	public List<ExchangeRate> getExchangeRates() {
		return exchangeRateRepository.findAll();
	}
}
