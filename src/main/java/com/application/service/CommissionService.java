package com.application.service;

import com.application.Entity.Commission;

import java.util.List;

public interface CommissionService {
	Commission createCommission(Commission commission);

	List<Commission> getAllCommissions();
}
