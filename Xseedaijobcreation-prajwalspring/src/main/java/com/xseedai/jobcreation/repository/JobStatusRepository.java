package com.xseedai.jobcreation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.JobStatus;

public interface JobStatusRepository extends JpaRepository<JobStatus, Long>{

}

