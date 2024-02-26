package com.xseedai.jobcreation.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xseedai.jobcreation.dto.JobCreationDto;
import com.xseedai.jobcreation.entity.City;
import com.xseedai.jobcreation.entity.ClientMaster;
import com.xseedai.jobcreation.entity.CompanyDetailsMaster;
import com.xseedai.jobcreation.entity.Country;
import com.xseedai.jobcreation.entity.Currency;
import com.xseedai.jobcreation.entity.JobCreation;
import com.xseedai.jobcreation.entity.JobStatus;
import com.xseedai.jobcreation.entity.JobTitleMaster;
import com.xseedai.jobcreation.entity.JobType;
import com.xseedai.jobcreation.entity.SkillsMaster;
import com.xseedai.jobcreation.entity.State;
import com.xseedai.jobcreation.entity.VmsMaster;
import com.xseedai.jobcreation.repository.CityRepository;
import com.xseedai.jobcreation.repository.ClientMasterRepository;
import com.xseedai.jobcreation.repository.CompanyDetailsRepository;
import com.xseedai.jobcreation.repository.CountryRepository;
import com.xseedai.jobcreation.repository.CurrencyRepository;
import com.xseedai.jobcreation.repository.JobCreationRepository;
import com.xseedai.jobcreation.repository.JobStatusRepository;
import com.xseedai.jobcreation.repository.JobTitleRespository;
import com.xseedai.jobcreation.repository.JobTypeRepository;
import com.xseedai.jobcreation.repository.SkillMasterRepository;
import com.xseedai.jobcreation.repository.StateRepository;
import com.xseedai.jobcreation.repository.VmsMasterRepository;
import com.xseedai.jobcreation.service.JobCreationService;

@Service
public class JobCreationServiceImpl implements JobCreationService {

	@Autowired
	private JobTypeRepository jobTypeRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	JobCreationRepository jobCreationRepository;

	@Autowired
	SkillMasterRepository skillMasterRepository;

	@Autowired
	private JobTitleRespository jobTitleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CompanyDetailsRepository companyDetailsRepository;

	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private JobStatusRepository jobStatusRepository;
	
	@Autowired
	private VmsMasterRepository vmsMasterRespository;
	
	@Autowired
	private ClientMasterRepository clientMasterRespository;

	// Post Api for adding skills in database
	@Override
	public ResponseEntity<?> addSkillsToDatabase(SkillsMaster skillMaster) {
		SkillsMaster existingSkill = skillMasterRepository.findBySkills(skillMaster.getSkills());
		if (existingSkill != null) {
			return ResponseEntity.badRequest().body("Skill already exists in the database");
		}
		skillMasterRepository.save(skillMaster);
		return new ResponseEntity<SkillsMaster>(skillMaster, HttpStatus.CREATED);
	}

	// Get Api for sending the list of skills to job creation screen for drop-down
	// menu
	@Override
	public List<SkillsMaster> getSkillsFromDatabase() {
		List<SkillsMaster> listOfSkills = skillMasterRepository.findAll();
		if (listOfSkills.isEmpty()) {
			throw new EmptyResultDataAccessException("No skills found in the database", 0);
		}
		return listOfSkills;
	}

	// Api for searching skills as a user types name of skill
	@Override
	public List<String> findMatchingSkills(String keyword) {
		List<SkillsMaster> skillsList = skillMasterRepository.findBySkillsContainingIgnoreCase(keyword);
		List<String> matchedSkills = new ArrayList<>();
		for (SkillsMaster skill : skillsList) {
			matchedSkills.add(skill.getSkills());
		}
		return matchedSkills;
	}

	@Override
	public JobTitleMaster saveJobTitle(JobTitleMaster jobTitle) {

		JobTitleMaster jobTitlemaster = new JobTitleMaster();
		jobTitlemaster.setJobTitle(jobTitle.getJobTitle());

		JobTitleMaster savedJobTitle = jobTitleRepository.save(jobTitlemaster);
		return savedJobTitle;
	}

	@Override
	public List<JobTitleMaster> getAllJobTitles() {
		return jobTitleRepository.findAll();
	}

	@Override
	public List<JobTitleMaster> searchJobTitles(String query) {
		List<JobTitleMaster> allJobTitles = jobTitleRepository.findAll();
		return allJobTitles.stream()
				.filter(jobTitle -> jobTitle.getJobTitle().toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public JobType postJobType(JobType jobType) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		jobType.setCreatedOn(currentDateTime);
		jobType.setModifiedOn(currentDateTime);
		return jobTypeRepository.save(jobType);
	}

	@Override
	public JobType getJobTypeById(Long jobTypeId) {
		Optional<JobType> optionalJobType = jobTypeRepository.findById(jobTypeId);
		return optionalJobType.orElse(null);
	}

	@Override
	public JobType getJobTypeByName(String jobTypeName) {
		return jobTypeRepository.findByJobTypeName(jobTypeName);
	}

	@Override
	public List<JobType> getAllJobTypes() {
		return jobTypeRepository.findAll();
	}

	@Override
	public Currency postCurrency(Currency currency) {
		return currencyRepository.save(currency);
	}

	@Override
	public Currency getCurrencyById(Long currencyId) {
		return currencyRepository.findById(currencyId).orElse(null);
	}

	@Override
	public Currency getCurrencyByName(String currencyName) {
		return currencyRepository.findByCurrencyName(currencyName);
	}

	@Override
	public List<Currency> getAllCurrencies() {
		return currencyRepository.findAll();
	}

	@Override
	public List<CompanyDetailsMaster> getAllCompanies() {
		return companyDetailsRepository.findAll();
	}

	@Override
	public CompanyDetailsMaster getCompanyById(Long id) {
		return companyDetailsRepository.findById(id).orElse(null);
	}

	@Override
	public CompanyDetailsMaster createCompany(CompanyDetailsMaster company) {
		company.setCreatedOn(LocalDateTime.now());
		return companyDetailsRepository.save(company);
	}

	@Override
	public JobStatus getJobStatusById(Long statusId) {
		return jobStatusRepository.findById(statusId).orElse(null);
	}

	@Override
	public List<JobStatus> getAllJobStatuses() {
		return jobStatusRepository.findAll();
	}

	@Override
	public JobStatus createJobStatus(JobStatus jobStatus) {
		// Set the creation timestamp
		jobStatus.setCreatedOn(LocalDateTime.now());

		// Save the job status entity
		return jobStatusRepository.save(jobStatus);
	}

	@Override
	public JobCreationDto createJob(JobCreationDto jobCreationDTO) {
		try {
			Optional<CompanyDetailsMaster> companies = companyDetailsRepository.findById(jobCreationDTO.getCompanyId());
			CompanyDetailsMaster company = companies.get();

			JobTitleMaster jobTitleMaster = jobTitleRepository.findById(jobCreationDTO.getJobTitleId())
					.orElseThrow(() -> new IllegalArgumentException(
							"Job Title not found with ID: " + jobCreationDTO.getJobTitleId()));
			JobStatus jobStatus = jobStatusRepository.findById(jobCreationDTO.getJobStatusId())
					.orElseThrow(() -> new IllegalArgumentException(
							"Job Status not found with ID: " + jobCreationDTO.getJobStatusId()));

			Optional<Currency> currencies = currencyRepository.findById(jobCreationDTO.getCurrencyId());
			Currency currency = currencies.get();

			Optional<JobType> jbtype = jobTypeRepository.findById(jobCreationDTO.getJobTypeId());
			JobType jobType = jbtype.get();
			if (jobType == null) {
				throw new IllegalArgumentException("Job Type not found with ID: " + jobCreationDTO.getJobTypeId());
			}
			Optional<Country> countries = countryRepository.findById(jobCreationDTO.getCountryId());
			Country country = countries.get();
			Optional<State> states = stateRepository.findById(jobCreationDTO.getStateId());
			State state = states.get();

			Optional<City> cities = cityRepository.findById(jobCreationDTO.getCityId());
			City city = cities.get();
			JobCreation jobCreation = new JobCreation();

			Optional<VmsMaster> vmsMasters = vmsMasterRespository.findById(jobCreationDTO.getVmsMasterId());
			VmsMaster vmsMaster = vmsMasters.get();

			Optional<ClientMaster> clientMasters = clientMasterRespository.findById(jobCreationDTO.getClientMasterId());
			ClientMaster clientMaster = clientMasters.get();

			jobCreation.setCompanyDetailsMaster(company);
			jobCreation.setJobTitle(jobTitleMaster);
			jobCreation.setJobStatus(jobStatus);
			jobCreation.setCurrency(currency);

			jobCreation.setJobtype(jobType);
			jobCreation.setCountry(country);
			jobCreation.setState(state);
			jobCreation.setCity(city);
			jobCreation.setVmsMaster(vmsMaster);
			jobCreation.setClientMaster(clientMaster);
			jobCreation.setJobCode(jobCreationDTO.getJobCode());
			jobCreation.setNumberOfOpenings(jobCreationDTO.getNumberOfOpenings());
			jobCreation.setMaxSubmission(jobCreationDTO.getMaxSubmission());
			jobCreation.setBillRate(jobCreationDTO.getBillRate());
			jobCreation.setMinimumPayRate(jobCreationDTO.getMinimumPayRate());
			jobCreation.setRequirementOpenDate(jobCreationDTO.getRequirementOpenDate());
			jobCreation.setRequirementCloseDate(jobCreationDTO.getRequirementCloseDate());
			jobCreation.setContractStartDate(jobCreationDTO.getContractStartDate());
			jobCreation.setContractEndDate(jobCreationDTO.getContractEndDate());
			jobCreation.setDuration(jobCreationDTO.getDuration());
			jobCreation.setJobDescription(jobCreationDTO.getJobDescription());
			jobCreation.setSkillId(jobCreationDTO.getSkillIds());
			jobCreation.setCreatedBy(jobCreationDTO.getCreatedBy());
			jobCreation.setCreatedOn(jobCreationDTO.getCreatedOn());
			jobCreation.setModifiedBy(jobCreationDTO.getModifiedBy());
			jobCreation.setModifiedOn(jobCreationDTO.getModifiedOn());

			// Save job entity
			JobCreation savedJob = jobCreationRepository.save(jobCreation);

			// Map the saved job entity back to JobCreationDto and return
			return modelMapper.map(savedJob, JobCreationDto.class);
		} catch (Exception e) {
			// Handle exceptions
			throw new RuntimeException("Failed to create job: " + e.getMessage());
		}
	}

//	@Override
//	public JobCreationDto updateJob(Long jobId, JobCreationDto jobCreationDto) {
//		try {
//			JobCreation existingJob = jobCreationRepository.findById(jobId)
//					.orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + jobId));
//
//			if (!existingJob.getJobStatus().getStatus().equalsIgnoreCase("Open")) {
//				throw new IllegalArgumentException("Job status is not open. Title cannot be updated.");
//			}
//
//			// Fetch related entities
//			CompanyDetailsMaster company = companyDetailsRepository.findById(jobCreationDto.getCompanyId()).orElseThrow(
//					() -> new IllegalArgumentException("Company not found with ID: " + jobCreationDto.getCompanyId()));
//
//			Currency currency = currencyRepository.findById(jobCreationDto.getCurrencyId())
//					.orElseThrow(() -> new IllegalArgumentException(
//							"Currency not found with ID: " + jobCreationDto.getCurrencyId()));
//
//			JobType jobType = jobTypeRepository.findById(jobCreationDto.getJobTypeId()).orElseThrow(
//					() -> new IllegalArgumentException("Job Type not found with ID: " + jobCreationDto.getJobTypeId()));
//
//			Country country = countryRepository.findById(jobCreationDto.getCountryId()).orElseThrow(
//					() -> new IllegalArgumentException("Country not found with ID: " + jobCreationDto.getCountryId()));
//
//			State state = stateRepository.findById(jobCreationDto.getStateId()).orElseThrow(
//					() -> new IllegalArgumentException("State not found with ID: " + jobCreationDto.getStateId()));
//
//			City city = cityRepository.findById(jobCreationDto.getCityId()).orElseThrow(
//					() -> new IllegalArgumentException("City not found with ID: " + jobCreationDto.getCityId()));
//
//			VmsMaster vmsMaster = vmsMasterRespository.findById(jobCreationDto.getVmsMasterId())
//					.orElseThrow(() -> new IllegalArgumentException(
//							"VMS Master not found with ID: " + jobCreationDto.getVmsMasterId()));
//
//			ClientMaster clientMaster = clientMasterRespository.findById(jobCreationDto.getClientMasterId())
//					.orElseThrow(() -> new IllegalArgumentException(
//							"Client Master not found with ID: " + jobCreationDto.getClientMasterId()));
//
//			// Update job entity
//			existingJob.setCompanyDetailsMaster(company);
//			existingJob.setJobStatus(jobStatusRepository.findById(jobCreationDto.getJobStatusId())
//					.orElseThrow(() -> new IllegalArgumentException(
//							"Job Status not found with ID: " + jobCreationDto.getJobStatusId())));
//			existingJob.setCurrency(currency);
//			existingJob.setJobtype(jobType);
//			existingJob.setCountry(country);
//			existingJob.setState(state);
//			existingJob.setCity(city);
//			existingJob.setVmsMaster(vmsMaster);
//			existingJob.setClientMaster(clientMaster);
//			existingJob.setJobCode(jobCreationDto.getJobCode());
//			existingJob.setNumberOfOpenings(jobCreationDto.getNumberOfOpenings());
//			existingJob.setMaxSubmission(jobCreationDto.getMaxSubmission());
//			existingJob.setBillRate(jobCreationDto.getBillRate());
//			existingJob.setMinimumPayRate(jobCreationDto.getMinimumPayRate());
//			existingJob.setRequirementOpenDate(jobCreationDto.getRequirementOpenDate());
//			existingJob.setRequirementCloseDate(jobCreationDto.getRequirementCloseDate());
//			existingJob.setContractStartDate(jobCreationDto.getContractStartDate());
//			existingJob.setContractEndDate(jobCreationDto.getContractEndDate());
//			existingJob.setDuration(jobCreationDto.getDuration());
//			existingJob.setJobDescription(jobCreationDto.getJobDescription());
//			existingJob.setSkillId(jobCreationDto.getSkillIds());
//			existingJob.setModifiedBy(jobCreationDto.getModifiedBy());
//			existingJob.setModifiedOn(LocalDateTime.now());
//
//			// Update job title if job status is open
//			if (existingJob.getJobStatus().getStatus().equals("Open")) {
//				JobTitleMaster jobTitleMaster = jobTitleRepository.findById(jobCreationDto.getJobTitleId())
//						.orElseThrow(() -> new IllegalArgumentException(
//								"Job Title not found with ID: " + jobCreationDto.getJobTitleId()));
//				existingJob.setJobTitle(jobTitleMaster);
//			}
//
//			// Save the updated job entity
//			JobCreation updatedJob = jobCreationRepository.save(existingJob);
//
//			// Map the updated job entity back to JobCreationDto and return
//			return modelMapper.map(updatedJob, JobCreationDto.class);
//		} catch (Exception e) {
//			// Handle exceptions
//			throw new RuntimeException("Failed to update job: " + e.getMessage());
//		}
//	}

	
	@Override
	public JobCreationDto updateJob(Long jobId, JobCreationDto jobCreationDto) {
		try {
			JobCreation existingJob = jobCreationRepository.findById(jobId)
					.orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + jobId));

//			if (!existingJob.getJobStatus().getStatus().equalsIgnoreCase("Open")) {
//				throw new IllegalArgumentException("Job status is not open. Title cannot be updated.");
//			}

			// Fetch related entities
			CompanyDetailsMaster company = companyDetailsRepository.findById(jobCreationDto.getCompanyId()).orElseThrow(
					() -> new IllegalArgumentException("Company not found with ID: " + jobCreationDto.getCompanyId()));

			Currency currency = currencyRepository.findById(jobCreationDto.getCurrencyId())
					.orElseThrow(() -> new IllegalArgumentException(
							"Currency not found with ID: " + jobCreationDto.getCurrencyId()));

			JobType jobType = jobTypeRepository.findById(jobCreationDto.getJobTypeId()).orElseThrow(
					() -> new IllegalArgumentException("Job Type not found with ID: " + jobCreationDto.getJobTypeId()));

			Country country = countryRepository.findById(jobCreationDto.getCountryId()).orElseThrow(
					() -> new IllegalArgumentException("Country not found with ID: " + jobCreationDto.getCountryId()));

			State state = stateRepository.findById(jobCreationDto.getStateId()).orElseThrow(
					() -> new IllegalArgumentException("State not found with ID: " + jobCreationDto.getStateId()));

			City city = cityRepository.findById(jobCreationDto.getCityId()).orElseThrow(
					() -> new IllegalArgumentException("City not found with ID: " + jobCreationDto.getCityId()));

			VmsMaster vmsMaster = vmsMasterRespository.findById(jobCreationDto.getVmsMasterId())
					.orElseThrow(() -> new IllegalArgumentException(
							"VMS Master not found with ID: " + jobCreationDto.getVmsMasterId()));

			ClientMaster clientMaster = clientMasterRespository.findById(jobCreationDto.getClientMasterId())
					.orElseThrow(() -> new IllegalArgumentException(
							"Client Master not found with ID: " + jobCreationDto.getClientMasterId()));

			JobStatus jobStatus = jobStatusRepository.findById(jobCreationDto.getJobStatusId())
					.orElseThrow(() -> new IllegalArgumentException(
							"Job Status Master not found with ID: " + jobCreationDto.getJobStatusId()));
			// Update job entity
			existingJob.setCompanyDetailsMaster(company);
//			existingJob.setJobStatus(jobStatusRepository.findById(jobCreationDto.getJobStatusId())
//					.orElseThrow(() -> new IllegalArgumentException(
//							"Job Status not found with ID: " + jobCreationDto.getJobStatusId())));
			if (existingJob.getJobStatus().getStatus().equals("Open")) {
				JobTitleMaster jobTitleMaster = jobTitleRepository.findById(jobCreationDto.getJobTitleId())
						.orElseThrow(() -> new IllegalArgumentException(
								"Job Title not found with ID: " + jobCreationDto.getJobTitleId()));
				existingJob.setJobTitle(jobTitleMaster);
			}
			else {
			    // Display message indicating that job title cannot be changed
				
			    System.out.println("Cannot change job title as job status is not Open.");
			}
//			if (existingJob.getJobStatus().getStatus().equalsIgnoreCase("Open")) {
//				existingJob.setJobtype(jobType);
//				//throw new IllegalArgumentException("Job status is not open. Title cannot be updated.");
//			}
			existingJob.setJobStatus(jobStatus);
			existingJob.setCurrency(currency);
			existingJob.setJobtype(jobType);
			existingJob.setCountry(country);
			existingJob.setState(state);
			existingJob.setCity(city);
			existingJob.setVmsMaster(vmsMaster);
			existingJob.setClientMaster(clientMaster);
			existingJob.setJobCode(jobCreationDto.getJobCode());
			existingJob.setNumberOfOpenings(jobCreationDto.getNumberOfOpenings());
			existingJob.setMaxSubmission(jobCreationDto.getMaxSubmission());
			existingJob.setBillRate(jobCreationDto.getBillRate());
			existingJob.setMinimumPayRate(jobCreationDto.getMinimumPayRate());
			existingJob.setRequirementOpenDate(jobCreationDto.getRequirementOpenDate());
			existingJob.setRequirementCloseDate(jobCreationDto.getRequirementCloseDate());
			existingJob.setContractStartDate(jobCreationDto.getContractStartDate());
			existingJob.setContractEndDate(jobCreationDto.getContractEndDate());
			existingJob.setDuration(jobCreationDto.getDuration());
			existingJob.setJobDescription(jobCreationDto.getJobDescription());
			existingJob.setSkillId(jobCreationDto.getSkillIds());
			existingJob.setModifiedBy(jobCreationDto.getModifiedBy());
			existingJob.setModifiedOn(LocalDateTime.now());

//			// Update job title if job status is open


			// Save the updated job entity
			JobCreation updatedJob = jobCreationRepository.save(existingJob);

			// Map the updated job entity back to JobCreationDto and return
			return modelMapper.map(updatedJob, JobCreationDto.class);
		} catch (Exception e) {
			// Handle exceptions
			throw new RuntimeException("Failed to update job: " + e.getMessage());
		}
	}
	
	
	@Override
	public void softDeleteJob(Long jobId) {
		JobCreation job = jobCreationRepository.findById(jobId)
				.orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + jobId));

		if (job.isDeleted()) {
			throw new IllegalArgumentException("Job with ID " + jobId + " is already deleted");
		}

		job.setDeleted(true);
		jobCreationRepository.save(job);
	}
}
