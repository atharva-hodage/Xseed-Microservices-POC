package com.xseedai.jobcreation.service;

import java.util.List;

import com.xseedai.jobcreation.entity.City;
import com.xseedai.jobcreation.entity.Country;
import com.xseedai.jobcreation.entity.State;
import com.xseedai.jobcreation.entity.VmsMaster;

public interface VmsService {
	
    VmsMaster createVmsMaster(VmsMaster vmsMaster,Long companyDetailsId);


    List<VmsMaster> getVmsByCompanyDetailsId(Long companyDetailsId);
	
}
