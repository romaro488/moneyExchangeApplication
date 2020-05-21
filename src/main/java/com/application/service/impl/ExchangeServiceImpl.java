package com.application.service.impl;

import com.application.Entity.ExchangeRequest;
import com.application.Entity.OperationType;
import com.application.repository.CommissionRepository;
import com.application.repository.ExchangeRateRepository;
import com.application.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

	private final CommissionRepository commissionRepository;
	private final ExchangeRateRepository rateRepository;

	@Override
	public ExchangeRequest exchange(ExchangeRequest exchangeRequest) {
		BigDecimal commissionPct = commissionRepository
				.findCommissionAmountByCurrency(
						exchangeRequest.getCurrencyFrom(),
						exchangeRequest.getCurrencyTo());

		if (commissionPct == null) {
			throw new RuntimeException("Please set commission pt for this pair");
		}

		if (OperationType.GIVE == exchangeRequest.getOperationType()) {
			setAmountTo(exchangeRequest, commissionPct);
		} else {
			setAmountFrom(exchangeRequest, commissionPct);
		}

		return exchangeRequest;
	}

	private void setAmountTo(ExchangeRequest exchangeRequest, BigDecimal commissionPct) {
		BigDecimal amountFrom = exchangeRequest.getAmountFrom();
		if (amountFrom == null) {
			throw new IllegalArgumentException("Please set amountFrom value");
		}

		BigDecimal exchangeRate = rateRepository
				.findRateAmountByCurrency(exchangeRequest.getCurrencyFrom(), exchangeRequest.getCurrencyTo());
		exchangeRequest.setAmountTo(calculateDirectExchange(exchangeRate, commissionPct, amountFrom));
	}

	private BigDecimal calculateDirectExchange(BigDecimal exchangeRate, BigDecimal commissionPct, BigDecimal amountFrom) {
		BigDecimal convertedCurrency = exchangeRate.multiply(amountFrom);
		BigDecimal commission = amountFrom.multiply(commissionPct).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);

		return convertedCurrency.subtract(commission).multiply(exchangeRate);
	}

	private void setAmountFrom(ExchangeRequest exchangeRequest, BigDecimal commissionPct) {
		BigDecimal amountTo = exchangeRequest.getAmountTo();
		if (amountTo == null) {
			throw new IllegalArgumentException("Please set amountTo value");
		}

		BigDecimal exchangeRate = rateRepository
				.findRateAmountByCurrency(exchangeRequest.getCurrencyTo(), exchangeRequest.getCurrencyFrom());
		exchangeRequest.setAmountFrom(calculateBackExchange(exchangeRate, commissionPct, amountTo));
	}

	private BigDecimal calculateBackExchange(BigDecimal exchangeRate, BigDecimal commissionPct, BigDecimal amountTo) {
		BigDecimal convertedCurrency = exchangeRate.multiply(amountTo);
		BigDecimal commission = convertedCurrency.multiply(commissionPct).divide(BigDecimal.valueOf(100), 2,
				BigDecimal.ROUND_HALF_UP);

		return convertedCurrency.add(commission);
	}

}
