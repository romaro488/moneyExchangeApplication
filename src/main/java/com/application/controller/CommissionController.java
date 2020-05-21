package com.application.controller;

import com.application.Entity.Commission;
import com.application.service.CommissionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommissionController {
	private final CommissionService commissionService;

	@PostMapping(path = "/commissions")
	@ApiOperation(value = "Установить значение комиссии для валютной пары.", authorizations = {
			@Authorization(value = "basicAuth")}, tags = {"commissions"})
	@ResponseStatus(HttpStatus.CREATED)
	public Commission postCommission(@RequestBody @Valid Commission commission) {
		return commissionService.createCommission(commission);
	}

	@ApiOperation(value = "Получить список установленных комиссий.", authorizations = {
			@Authorization(value = "basicAuth")}, tags = {"commissions"})
	@GetMapping(path = "/commissions")
	public List<Commission> findAllCommissions() {
		return commissionService.getAllCommissions();
	}
}
