package com.application.service.impl;

import com.application.Entity.ExchangeRate;
import com.application.repository.ExchangeRateRepository;
import com.application.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
	private final ExchangeRateRepository exchangeRateRepository;

	@Override
	public ExchangeRate saveExchangeRate(ExchangeRate exchangeRate) {
		ExchangeRate rate = exchangeRateRepository.findExchangeRateByCurrency(
				exchangeRate.getCurrencyFrom(),
				exchangeRate.getCurrencyTo());

		BigDecimal newRate = BigDecimal.ONE.divide(exchangeRate.getRate(), 2, BigDecimal.ROUND_HALF_UP);

		if (rate != null) {
			return updateExchangeRate(exchangeRate, rate, newRate);
		}

		ExchangeRate oppositeRate = new ExchangeRate(exchangeRate.getCurrencyTo(), exchangeRate.getCurrencyFrom(), newRate);

		return exchangeRateRepository.save(oppositeRate);
	}

	@Override
	public List<ExchangeRate> getExchangeRates() {
		return exchangeRateRepository.findAll();
	}

	private ExchangeRate updateExchangeRate(ExchangeRate exchangeRate, ExchangeRate rate, BigDecimal newRate) {
		ExchangeRate oppositeRate = exchangeRateRepository.findExchangeRateByCurrency(
				exchangeRate.getCurrencyTo(),
				exchangeRate.getCurrencyTo());
		oppositeRate.setRate(newRate);

		exchangeRateRepository.save(exchangeRate);
		rate.setRate(oppositeRate.getRate());

		return exchangeRateRepository.save(rate);
	}
}
