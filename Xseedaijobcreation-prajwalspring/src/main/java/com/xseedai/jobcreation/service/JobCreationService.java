package com.xseedai.jobcreation.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.xseedai.jobcreation.dto.JobCreationDto;
import com.xseedai.jobcreation.entity.CompanyDetailsMaster;
import com.xseedai.jobcreation.entity.Currency;
import com.xseedai.jobcreation.entity.JobCreation;
import com.xseedai.jobcreation.entity.JobStatus;
import com.xseedai.jobcreation.entity.JobTitleMaster;
import com.xseedai.jobcreation.entity.JobType;
import com.xseedai.jobcreation.entity.SkillsMaster;

public interface JobCreationService {

	ResponseEntity<?> addSkillsToDatabase(SkillsMaster skillMaster);

	List<SkillsMaster> getSkillsFromDatabase();

	List<String> findMatchingSkills(String keyword);

	// below 3 for Job title functionality
	JobTitleMaster saveJobTitle(JobTitleMaster jobTitle);

	List<JobTitleMaster> getAllJobTitles();

	List<JobTitleMaster> searchJobTitles(String query);

	JobType postJobType(JobType jobType);

	JobType getJobTypeById(Long jobTypeId);

	JobType getJobTypeByName(String jobTypeName);

	List<JobType> getAllJobTypes();

	Currency postCurrency(Currency currency);

	Currency getCurrencyById(Long currencyId);

	Currency getCurrencyByName(String currencyName);

	List<Currency> getAllCurrencies();

	JobCreationDto createJob(JobCreationDto jobCreationDTO);

	List<CompanyDetailsMaster> getAllCompanies();

	CompanyDetailsMaster getCompanyById(Long id);

	CompanyDetailsMaster createCompany(CompanyDetailsMaster company);

	JobStatus getJobStatusById(Long statusId);

	List<JobStatus> getAllJobStatuses();

	JobStatus createJobStatus(JobStatus jobStatus);

	JobCreationDto updateJob(Long jobId, JobCreationDto jobCreationDto);

	void softDeleteJob(Long jobId);

}
