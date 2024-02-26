package com.xseedai.jobcreation.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.xseedai.jobcreation.entity.JobType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobCreationDto {

	private Long jobId;

	private String jobCode;

	private Long jobTitleId;

	private Long vmsMasterId;
	
	private Long clientMasterId;
	
	private Long companyId;


	private Long jobTypeId;

	private Long countryId;

	private Long stateId;

	private Long cityId;

	private int numberOfOpenings;

	private Long maxSubmission;

	private Long billRate;

	private Long minimumPayRate;

	private Date requirementOpenDate;

	private Date requirementCloseDate;

	private Date contractStartDate;

	private Date contractEndDate;

	private Long duration;

	private Long currencyId;
	
	private Long jobStatusId;

	private String createdBy;

	private LocalDateTime createdOn;

	private String modifiedBy;

	private LocalDateTime modifiedOn;

	private String jobDescription;

	private List<Long> skillIds;
}