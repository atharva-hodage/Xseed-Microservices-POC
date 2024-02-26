package com.xseedai.jobcreation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
	  Optional<Country> findByCountryName(String countryName);
}
