package com.xseedai.jobcreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.JobType;

public interface JobTypeRepository extends JpaRepository<JobType, Long> {
	JobType findByJobTypeName(String jobTypeName);

	JobType findByJobtypeId(Long jobtypeId);

}
