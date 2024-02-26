package com.xseedai.jobcreation.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import com.xseedai.jobcreation.dto.JobListingDto;

public interface JobListingService {

	 Page<JobListingDto> getAllJobs(Pageable pageable);
	 Page<JobListingDto> getAllFilteredJob(List<Long> statuses, List<Long> companies, List<Long> vms,
             List<String> expiring, List<Long> jobTypes, List<Long> clients,
             Pageable pageable);

//	List<JobListingDto> getAllFilteredJobsStringType(List<String> statuses, List<String> companies, List<String> vms, List<String> expiring,
//			List<String> jobTypes, List<String> clients);

	List<JobListingDto> getAllFilteredJob(List<Long> statuses, List<Long> companies, List<Long> vms,
			List<String> expiring, List<Long> jobTypes, List<Long> clients);

}
