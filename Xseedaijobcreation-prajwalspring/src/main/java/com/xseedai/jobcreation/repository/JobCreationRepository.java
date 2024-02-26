package com.xseedai.jobcreation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.JobCreation;

public interface JobCreationRepository extends JpaRepository<JobCreation, Long> {
	Page<JobCreation> findByDeletedFalse(Pageable pageable);

	Page<JobCreation> findByJobTitleContainingIgnoreCaseAndDeletedFalse(String title, Pageable pageable);
}
