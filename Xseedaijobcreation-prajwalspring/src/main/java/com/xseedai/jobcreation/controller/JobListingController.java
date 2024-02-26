package com.xseedai.jobcreation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseedai.jobcreation.dto.JobListingDto;
import com.xseedai.jobcreation.service.JobListingService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/joblisting")
@AllArgsConstructor
public class JobListingController {

	@Autowired
	private JobListingService jobListingService;


	

	@GetMapping("/getAllJobs")
	public Page<JobListingDto> getAllJobs(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return jobListingService.getAllJobs(pageable);
	}
//
//	@GetMapping("/getAllFilteredJobsStringType")
//	public List<JobListingDto> getAllFilteredJobsStringType(@RequestParam(required = false) List<String> statuses,
//			@RequestParam(required = false) List<String> companies, @RequestParam(required = false) List<String> vms,
//			@RequestParam(required = false) List<String> expiring,
//			@RequestParam(required = false) List<String> jobTypes,
//			@RequestParam(required = false) List<String> clients) {
//
//		return jobListingService.getAllFilteredJobsStringType(statuses, companies, vms, expiring, jobTypes, clients);
//	}

//	@GetMapping("/getAllFilteredJobs")
//	public List<JobListingDto> getAllFilteredJobs(@RequestParam(required = false) List<Long> statuses,
//			@RequestParam(required = false) List<Long> companies, @RequestParam(required = false) List<Long> vms,
//			@RequestParam(required = false) List<String> expiring, @RequestParam(required = false) List<Long> jobTypes,
//			@RequestParam(required = false) List<Long> clients) {
//
//		return jobListingService.getAllFilteredJob(statuses, companies, vms, expiring, jobTypes, clients);
//	}

    @GetMapping("/getAllFilteredJobs")
    public ResponseEntity<Page<JobListingDto>> getAllFilteredJobs(@RequestParam(required = false) List<Long> statuses,
                                                                  @RequestParam(required = false) List<Long> companies,
                                                                  @RequestParam(required = false) List<Long> vms,
                                                                  @RequestParam(required = false) List<String> expiring,
                                                                  @RequestParam(required = false) List<Long> jobTypes,
                                                                  @RequestParam(required = false) List<Long> clients,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<JobListingDto> filteredJobs = jobListingService.getAllFilteredJob(statuses, companies, vms,
                                                                              expiring, jobTypes, clients,
                                                                              pageable);
        return ResponseEntity.ok(filteredJobs);
    }
}
