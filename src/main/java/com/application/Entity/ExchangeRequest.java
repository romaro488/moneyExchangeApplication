package com.application.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {

	private BigDecimal amountFrom;

	private BigDecimal amountTo;

	Currency currencyFrom;

	Currency currencyTo;

	OperationType operationType;
}
