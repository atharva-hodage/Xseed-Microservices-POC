package com.xseedai.jobcreation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.CompanyDetailsMaster;

public interface CompanyDetailsRepository extends JpaRepository<CompanyDetailsMaster, Long>{
    Optional<CompanyDetailsMaster> findByCompanyName(String companyName);

}

