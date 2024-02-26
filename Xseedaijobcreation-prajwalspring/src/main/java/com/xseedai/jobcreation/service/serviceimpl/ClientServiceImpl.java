package com.xseedai.jobcreation.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xseedai.jobcreation.entity.ClientMaster;

import com.xseedai.jobcreation.entity.VmsMaster;
import com.xseedai.jobcreation.repository.ClientMasterRepository;

import com.xseedai.jobcreation.repository.VmsMasterRepository;
import com.xseedai.jobcreation.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	private VmsMasterRepository vmsMasterRepository;
	@Autowired
	private ClientMasterRepository clientMasterRepository;
	@Override
	public ClientMaster createClient(ClientMaster clientMaster, Long vmsId) {
		 VmsMaster vmsMaster = vmsMasterRepository.findById(vmsId)
	                .orElseThrow(() -> new IllegalArgumentException("VMS details not found with ID: " + vmsId));

	        // Associate the VMS with the retrieved company
		 clientMaster.setVmsMaster(vmsMaster);

	        // Set created and modified details
		 clientMaster.setCreatedBy("prajwal");
		 clientMaster.setCreatedOn(LocalDateTime.now());
		 clientMaster.setModifiedBy(vmsMaster.getCreatedBy());
		 clientMaster.setModifiedOn(vmsMaster.getCreatedOn());

	        // Save the VMS
	        return clientMasterRepository.save(clientMaster);
	}
	@Override
	public List<ClientMaster> getClientsByVmsId(Long vmsId) {
        return clientMasterRepository.findByVmsMaster_VmsId(vmsId);

	}
	}


