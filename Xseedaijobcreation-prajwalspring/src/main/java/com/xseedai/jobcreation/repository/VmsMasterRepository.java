package com.xseedai.jobcreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.VmsMaster;



public interface VmsMasterRepository extends JpaRepository<VmsMaster, Long>{
    List<VmsMaster> findByCompanyDetailsMaster_CompanyDetailsId(Long companyDetailsId);

}
