package com.xseedai.jobcreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.State;

public interface StateRepository extends JpaRepository<State, Long>{
	
	List<State> findByCountryCountryId(Long countryId);

}

