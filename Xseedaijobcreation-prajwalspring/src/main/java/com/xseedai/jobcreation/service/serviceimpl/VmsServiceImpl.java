package com.xseedai.jobcreation.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xseedai.jobcreation.entity.CompanyDetailsMaster;
import com.xseedai.jobcreation.entity.Country;
import com.xseedai.jobcreation.entity.VmsMaster;
import com.xseedai.jobcreation.repository.CompanyDetailsRepository;
import com.xseedai.jobcreation.repository.VmsMasterRepository;
import com.xseedai.jobcreation.service.VmsService;

@Service
public class VmsServiceImpl implements VmsService {

	@Autowired
	private VmsMasterRepository vmsMasterRepository;
	@Autowired
	private CompanyDetailsRepository companyDetailMasterRepository;

	@Override
	public VmsMaster createVmsMaster(VmsMaster vmsMaster, Long companyDetailsId) {
		CompanyDetailsMaster companyDetails = companyDetailMasterRepository.findById(companyDetailsId).orElseThrow(
				() -> new IllegalArgumentException("Company details not found with ID: " + companyDetailsId));

		// Associate the VMS with the retrieved company
		vmsMaster.setCompanyDetailsMaster(companyDetails);

		// Set created and modified details
		vmsMaster.setCreatedBy("prajwal");
		vmsMaster.setCreatedOn(LocalDateTime.now());
		vmsMaster.setModifiedBy(vmsMaster.getCreatedBy());
		vmsMaster.setModifiedOn(vmsMaster.getCreatedOn());

		// Save the VMS
		return vmsMasterRepository.save(vmsMaster);
	}

	@Override
	public List<VmsMaster> getVmsByCompanyDetailsId(Long companyDetailsId) {
		return vmsMasterRepository.findByCompanyDetailsMaster_CompanyDetailsId(companyDetailsId);
	}

}
