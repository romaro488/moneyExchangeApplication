package com.application.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRequest {
	@DecimalMin(value = "0.00", message = "Amount should be grater than 0.00 ")
	private BigDecimal amountFrom;

	@DecimalMin(value = "0.00", message = "Amount should be grater than 0.00 ")
	private BigDecimal amountTo;

	@Enumerated(EnumType.STRING)
	@NotNull
	Currency currencyFrom;

	@Enumerated(EnumType.STRING)
	@NotNull
	Currency currencyTo;

	OperationType operationType;

	public ExchangeRequest formatResult() {
		setAmountTo(amountTo.setScale(2, BigDecimal.ROUND_HALF_UP));
		setAmountFrom(amountFrom.setScale(2, BigDecimal.ROUND_HALF_UP));
		return this;
	}
}
