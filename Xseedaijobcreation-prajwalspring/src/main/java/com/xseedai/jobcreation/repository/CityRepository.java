package com.xseedai.jobcreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	List<City> findByStateStateId(Long stateId);
}
