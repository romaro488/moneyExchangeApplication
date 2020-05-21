package com.application.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {


	public ExchangeRate(Currency currencyTo, Currency currencyFrom, BigDecimal rate) {
		this.currencyTo = currencyTo;
		this.currencyFrom = currencyFrom;
		this.rate = rate;
	}

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(name = "currency_from")
	private Currency currencyFrom;

	@DecimalMin(value = "0.00")
	@Column(name = "rate")
	private BigDecimal rate;

	@Enumerated(EnumType.STRING)
	@Column(name = "currency_to")
	@NotNull
	private Currency currencyTo;
}
