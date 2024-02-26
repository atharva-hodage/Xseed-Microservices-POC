package com.xseedai.jobcreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseedai.jobcreation.entity.VmsMaster;
import com.xseedai.jobcreation.service.LocationService;
import com.xseedai.jobcreation.service.VmsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/jobcreation")
@AllArgsConstructor

public class VmsController {

	@Autowired
	private VmsService vmsService;

	 @PostMapping("/addvms/{companyDetailsId}")
	    public ResponseEntity<VmsMaster> createVmsMaster(@RequestBody VmsMaster vmsMaster, @PathVariable Long companyDetailsId) {
	        VmsMaster createdVmsMaster = vmsService.createVmsMaster(vmsMaster, companyDetailsId);
	        return new ResponseEntity<>(createdVmsMaster, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/getvms/{companyDetailsId}")
	    public ResponseEntity<List<VmsMaster>> getVmsByCompanyDetailsId(@PathVariable Long companyDetailsId) {
	        List<VmsMaster> vmsList = vmsService.getVmsByCompanyDetailsId(companyDetailsId);
	        if (vmsList.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<>(vmsList, HttpStatus.OK);
	    }
}
