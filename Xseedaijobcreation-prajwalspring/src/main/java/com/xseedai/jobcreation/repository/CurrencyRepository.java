package com.xseedai.jobcreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.Currency;
import com.xseedai.jobcreation.entity.JobType;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	Currency findByCurrencyName(String currencyName);

	Currency findByCurrencyId(Long currencyId);

}
