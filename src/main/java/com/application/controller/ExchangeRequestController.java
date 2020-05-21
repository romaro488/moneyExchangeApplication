package com.application.controller;

import com.application.Entity.ExchangeRequest;
import com.application.Entity.OperationType;
import com.application.service.ExchangeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ExchangeRequestController {

	private final ExchangeService exchangeService;

	@ApiOperation(value = "Запрос обмена валют.", response = ExchangeRequest.class, authorizations = {
			@Authorization(value = "basicAuth")}, tags = {"exchange"})
	@PostMapping(path = "/exchange")
	ExchangeRequest exchange(@RequestBody @Valid ExchangeRequest exchangeRequest) {
		return exchangeService.exchange(exchangeRequest);
	}
}
