package com.application.service.impl;

import com.application.Entity.Commission;
import com.application.repository.CommissionRepository;
import com.application.service.CommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommissionServiceImpl implements CommissionService {
	private CommissionRepository commissionRepository;

	@Override
	public Commission createCommission(Commission commission) {
		Commission commissionByCurrency = commissionRepository.findCommissionByCurrency(commission.getFromCurrency(), commission.getToCurrency());
		if (commissionByCurrency != null) {
			return updateCommission(commission, commission.getCommissionPct());
		}
		return commissionRepository.save(commission);
	}

	@Override
	public List<Commission> getAllCommissions() {
		return commissionRepository.findAll();
	}

	private Commission updateCommission(Commission commission, BigDecimal commissionPt) {
		commission.setCommissionPct(commissionPt);
		return commissionRepository.save(commission);
	}
}
