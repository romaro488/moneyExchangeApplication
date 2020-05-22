package com.application.service.impl;

import com.application.Entity.Commission;
import com.application.Entity.ExchangeRate;
import com.application.Entity.ExchangeRequest;
import com.application.repository.CommissionRepository;
import com.application.repository.ExchangeRateRepository;
import com.application.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.application.Entity.OperationType.GIVE;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

	private final CommissionRepository commissionRepository;
	private final ExchangeRateRepository rateRepository;

	@Override
	public ExchangeRequest exchange(ExchangeRequest exchangeRequest) {
		Commission commission;
		ExchangeRate rate;
		if (exchangeRequest.getOperationType().equals(GIVE)) {
			commission = commissionRepository.
					findCommissionByCurrency(exchangeRequest.getCurrencyFrom(), exchangeRequest.getCurrencyTo());
			rate = rateRepository.
					findExchangeRateByCurrency(exchangeRequest.getCurrencyFrom(), exchangeRequest.getCurrencyTo());
		} else {
			commission = commissionRepository.
					findCommissionByCurrency(exchangeRequest.getCurrencyTo(), exchangeRequest.getCurrencyFrom());
			rate = rateRepository.
					findExchangeRateByCurrency(exchangeRequest.getCurrencyTo(), exchangeRequest.getCurrencyFrom());
		}
		switch (exchangeRequest.getOperationType()) {
			case GET:
				setupExchangeGet(exchangeRequest, commission, rate);
				break;
			case GIVE:
				setupExchangeGive(exchangeRequest, commission, rate);
				break;
		}
		return exchangeRequest;
	}

	private void setupExchangeGet(ExchangeRequest exchange, Commission commission, ExchangeRate rate) {
		BigDecimal commissionAmount;
		commissionAmount = exchange.getAmountTo()
				.divide(BigDecimal.valueOf(100))
				.multiply(commission.getCommissionPct());
		exchange.setAmountFrom(exchange.getAmountTo().add(commissionAmount).multiply(rate.getRate()));
		exchange.formatResult();
	}

	private void setupExchangeGive(ExchangeRequest exchange, Commission commission, ExchangeRate rate) {
		BigDecimal commissionAmount;
		commissionAmount = exchange.getAmountFrom()
				.divide(BigDecimal.valueOf(100))
				.multiply(commission.getCommissionPct());
		exchange.setAmountTo(exchange.getAmountFrom().subtract(commissionAmount).multiply(rate.getRate()));
		exchange.formatResult();
	}
}
