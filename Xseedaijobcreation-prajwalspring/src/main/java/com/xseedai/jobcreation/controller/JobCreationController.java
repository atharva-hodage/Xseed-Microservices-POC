package com.xseedai.jobcreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseedai.jobcreation.dto.JobCreationDto;
import com.xseedai.jobcreation.entity.CompanyDetailsMaster;
import com.xseedai.jobcreation.entity.Currency;
import com.xseedai.jobcreation.entity.JobCreation;
import com.xseedai.jobcreation.entity.JobStatus;
import com.xseedai.jobcreation.entity.JobTitleMaster;
import com.xseedai.jobcreation.entity.JobType;
import com.xseedai.jobcreation.entity.SkillsMaster;
import com.xseedai.jobcreation.service.JobCreationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/jobcreation")
@AllArgsConstructor
public class JobCreationController {
	@Autowired
	private JobCreationService jobCreationService;

	@PostMapping("/addjobtitle")
	public ResponseEntity<JobTitleMaster> saveJobTitle(@RequestBody JobTitleMaster jobTitle) {
		JobTitleMaster saveJobTitle = jobCreationService.saveJobTitle(jobTitle);
		return new ResponseEntity<>(saveJobTitle, HttpStatus.CREATED);
	}

	@GetMapping("/getalljobtitle")
	public ResponseEntity<List<JobTitleMaster>> getAllJobTitles() {
		List<JobTitleMaster> jobTitles = jobCreationService.getAllJobTitles();
		return ResponseEntity.ok().body(jobTitles);
	}

	@GetMapping("/search")
	public ResponseEntity<List<JobTitleMaster>> searchJobTitles(@RequestParam("query") String query) {
		List<JobTitleMaster> jobTitles = jobCreationService.searchJobTitles(query);
		return ResponseEntity.ok().body(jobTitles);
	}

	@PostMapping("/addSkillsToDatabase")
	public ResponseEntity<?> addSkillsToDatabase(@RequestBody SkillsMaster skillMaster) {
		return jobCreationService.addSkillsToDatabase(skillMaster);
	}

	@GetMapping("/getSkillsFromDatabase")
	public ResponseEntity<?> getSkillsFromDatabase() {
		return new ResponseEntity<>(jobCreationService.getSkillsFromDatabase(), HttpStatus.FOUND);
	}

	@GetMapping("/searchSkill")
	public ResponseEntity<List<String>> searchSkills(@RequestParam String keyword) {
		List<String> matchedSkills = jobCreationService.findMatchingSkills(keyword);
		return ResponseEntity.ok(matchedSkills);
	}

	@PostMapping("/saveJobType")
	public ResponseEntity<JobType> postJobType(@RequestBody JobType jobType) {
		JobType createdJobType = jobCreationService.postJobType(jobType);
		return new ResponseEntity<>(createdJobType, HttpStatus.CREATED);
	}

//
	@GetMapping("/getJobType")
	public ResponseEntity<JobType> getJobTypeById(@RequestParam Long jobTypeId) {
		JobType jobType = jobCreationService.getJobTypeById(jobTypeId);
		if (jobType != null) {
			return new ResponseEntity<>(jobType, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getJobTypeByName")
	public ResponseEntity<JobType> getJobTypeByName(@RequestParam String jobTypeName) {
		JobType jobType = jobCreationService.getJobTypeByName(jobTypeName);
		if (jobType != null) {
			return new ResponseEntity<>(jobType, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getJobTypeList")
	public ResponseEntity<List<JobType>> getAllJobTypes() {
		List<JobType> jobTypes = jobCreationService.getAllJobTypes();
		return new ResponseEntity<>(jobTypes, HttpStatus.OK);
	}

	@PostMapping("/saveCurrency")
	public ResponseEntity<Currency> postCurrency(@RequestBody Currency currency) {
		Currency createdCurrency = jobCreationService.postCurrency(currency);
		return new ResponseEntity<>(createdCurrency, HttpStatus.CREATED);
	}

	@GetMapping("/getCurrencyById/{currencyId}")
	public ResponseEntity<Currency> getCurrencyById(@PathVariable Long currencyId) {
		Currency currency = jobCreationService.getCurrencyById(currencyId);
		if (currency != null) {
			return new ResponseEntity<>(currency, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getCurrencyByName/{currencyName}")
	public ResponseEntity<Currency> getCurrencyByName(@PathVariable String currencyName) {
		Currency currency = jobCreationService.getCurrencyByName(currencyName);
		if (currency != null) {
			return new ResponseEntity<>(currency, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllCurrencies")
	public ResponseEntity<List<Currency>> getAllCurrencies() {
		List<Currency> currencies = jobCreationService.getAllCurrencies();
		return new ResponseEntity<>(currencies, HttpStatus.OK);
	}

	@GetMapping("/getAllCompanies")
	public ResponseEntity<List<CompanyDetailsMaster>> getAllCompanies() {
		List<CompanyDetailsMaster> companies = jobCreationService.getAllCompanies();
		return ResponseEntity.ok(companies);
	}

	@GetMapping("/getCompanyById/{id}")
	public ResponseEntity<CompanyDetailsMaster> getCompanyById(@PathVariable Long id) {
		CompanyDetailsMaster company = jobCreationService.getCompanyById(id);
		if (company != null) {
			return ResponseEntity.ok(company);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/saveCompany")
	public ResponseEntity<CompanyDetailsMaster> createCompany(@RequestBody CompanyDetailsMaster company) {
		CompanyDetailsMaster createdCompany = jobCreationService.createCompany(company);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdCompany);
	}

	@GetMapping("/getJobStatusById/{statusId}")
	public ResponseEntity<JobStatus> getJobStatusById(@PathVariable Long statusId) {
		JobStatus jobStatus = jobCreationService.getJobStatusById(statusId);
		if (jobStatus != null) {
			return ResponseEntity.ok(jobStatus);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getJobStatusAll")
	public ResponseEntity<List<JobStatus>> getAllJobStatuses() {
		List<JobStatus> jobStatuses = jobCreationService.getAllJobStatuses();
		return ResponseEntity.ok(jobStatuses);
	}

	@PostMapping("/addJobStatus")
	public ResponseEntity<JobStatus> createJobStatus(@RequestBody JobStatus jobStatus) {
		JobStatus createdJobStatus = jobCreationService.createJobStatus(jobStatus);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdJobStatus);
	}

	@PostMapping("/create")
	public ResponseEntity<JobCreationDto> createJob(@RequestBody JobCreationDto jobCreationDto) {
		try {
			JobCreationDto createdJob = jobCreationService.createJob(jobCreationDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<JobCreationDto> updateJob(@RequestParam Long jobId,
			@RequestBody JobCreationDto jobCreationDto) {
		try {
			JobCreationDto updatedJob = jobCreationService.updateJob(jobId, jobCreationDto);
			return ResponseEntity.ok(updatedJob);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteJob(@RequestParam Long jobId) {
		try {
			jobCreationService.softDeleteJob(jobId);
			return ResponseEntity.ok("Job with ID " + jobId + " has been successfully deleted");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete job: " + e.getMessage());
		}
	}
}
