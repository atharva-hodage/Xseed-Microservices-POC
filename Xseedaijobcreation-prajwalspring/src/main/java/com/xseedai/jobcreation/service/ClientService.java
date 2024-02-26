 package com.xseedai.jobcreation.service;

import java.util.List;

import com.xseedai.jobcreation.entity.ClientMaster;

public interface ClientService {
	  ClientMaster createClient(ClientMaster clientMaster,Long vmsId);
	    List<ClientMaster> getClientsByVmsId(Long vmsId);

}
